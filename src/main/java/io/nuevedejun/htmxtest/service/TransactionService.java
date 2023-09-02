package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.dto.TransactionData;
import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository repository;

	public List<TransactionData> getLatestTransactions(int page, int size) {
		var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "date");
		return repository.findAll(pageable).stream()
				.map(this::toDTO).toList();
	}

	private TransactionData toDTO(Transaction transaction) {
		return new TransactionData(
				transaction.getDate(),
				transaction.getAccount(),
				transaction.getCategory(),
				transaction.getNote(),
				transaction.getAmount()
		);
	}

	public record PageItem(int number, boolean selected, boolean blank) {
		public static final PageItem GAP = new PageItem(-1, false, true);
	}

	/**
	 * Return a list of pages to show in the pagination of transactions.
	 *
	 * @param page   the currently selected page
	 * @param size   the page size
	 * @param window the window size around the selected page
	 * @return the list of pages
	 */
	public List<PageItem> getPagination(int page, int size, int window) {
		final long count = repository.count();
		final int allPages = (int) Math.ceilDiv(count, size);

		final int slider = 2 * window + 1;
		final int lower = Math.max(Math.min(page - window, allPages - 1 - slider), 2);
		final int upper = Math.min(Math.max(page + window, 2 + slider), allPages - 1);

		var result = new ArrayList<PageItem>(4 + slider);
		result.add(selectedPageItem(page, 1));
		if (lower == 3) {
			result.add(selectedPageItem(page, 2));
		} else if (lower > 3) {
			result.add(PageItem.GAP);
		}
		result.addAll(IntStream.rangeClosed(lower, upper)
				.mapToObj(i -> selectedPageItem(page, i))
				.toList());
		if (upper == allPages - 2) {
			result.add(selectedPageItem(page, allPages - 1));
		} else if (upper < allPages - 2) {
			result.add(PageItem.GAP);
		}
		result.add(selectedPageItem(page, allPages));

		return List.copyOf(result);
	}

	private PageItem selectedPageItem(int page, int item) {
		return new PageItem(item, page == item, false);
	}
}
