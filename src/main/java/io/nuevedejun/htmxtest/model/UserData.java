package io.nuevedejun.htmxtest.model;

import io.nuevedejun.htmxtest.dto.OperationData;
import io.nuevedejun.htmxtest.entity.User;
import io.nuevedejun.htmxtest.entity.User.Preferences;
import io.nuevedejun.htmxtest.service.OperationService;
import io.nuevedejun.htmxtest.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Provide accessors for relevant user information to be used in web templates.
 */
@RequiredArgsConstructor
public class UserData {
	public static final int FIRST_PAGE = 1;
	public static final int FALLBACK_SIZE = ModelData.PAGE_SIZES.first();
	public static final int FALLBACK_WINDOW = 3;
	private static final int SLIDER = 2 * FALLBACK_WINDOW + 1;

	private final OperationService operationService;
	private final UserService userService;
	@Getter
	private final int page;

	/**
	 * Page size. If null, get the user preference from DB.
	 */
	@Nullable
	private Integer size;

	@Nullable
	private Page<OperationData> operationPage;
	@Nullable
	private List<OperationData> operations;
	@Nullable
	private List<PageItem> pagination;

	public UserData(OperationService operationService, UserService userService, int page, @Nullable Integer size) {
		this(operationService, userService, page);
		this.size = size;
	}

	private Page<OperationData> getOperationPage() {
		if (operationPage == null) {
			operationPage = operationService.getLatestOperations(page - 1, getSize());
		}
		return operationPage;
	}

	public List<OperationData> getOperations() {
		if (operations == null) {
			operations = getOperationPage().toList();
		}
		return operations;
	}

	public record PageItem(int number, boolean selected, boolean blank) {
		public static final PageItem GAP = new PageItem(-1, false, true);
	}

	public List<PageItem> getPagination() {
		if (pagination == null) {
			pagination = buildPagination();
		}
		return pagination;
	}

	/**
	 * Builds a list of pagination items depending on the total amount of pages. It takes into account the desired number
	 * of pages to display around the current page to calculate the size and contents of the list, and always
	 * generate the same amount of items.
	 *
	 * @return a list of pagination items
	 */
	private List<PageItem> buildPagination() {
		final int totalPages = getOperationPage().getTotalPages();

		if (totalPages < 2) {
			return List.of(selectedPageItem(page, 1));
		}

		final int lower = Math.max(Math.min(page - FALLBACK_WINDOW, totalPages - 1 - SLIDER), 2);
		final int upper = Math.min(Math.max(page + FALLBACK_WINDOW, 2 + SLIDER), totalPages - 1);

		var result = new ArrayList<PageItem>(4 + SLIDER);
		result.add(selectedPageItem(page, 1));
		if (lower == 3) {
			result.add(selectedPageItem(page, 2));
		} else if (lower > 3) {
			result.add(PageItem.GAP);
		}
		result.addAll(IntStream.rangeClosed(lower, upper)
				.mapToObj(i -> selectedPageItem(page, i))
				.toList());
		if (upper == totalPages - 2) {
			result.add(selectedPageItem(page, totalPages - 1));
		} else if (upper < totalPages - 2) {
			result.add(PageItem.GAP);
		}
		result.add(selectedPageItem(page, totalPages));

		return List.copyOf(result);
	}

	private PageItem selectedPageItem(int page, int item) {
		return new PageItem(item, page == item, false);
	}

	public int getSize() {
		if (size == null) {
			size = userService.getUser()
					.map(User::getPreferences)
					.map(Preferences::pageSize)
					.orElse(FALLBACK_SIZE);
		}
		return size;
	}
}
