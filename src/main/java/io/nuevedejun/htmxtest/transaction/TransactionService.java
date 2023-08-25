package io.nuevedejun.htmxtest.transaction;

import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository repository;

	public List<TransactionModel> getLatestTransactions(int page, int size) {
		var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "date");
		return repository.findAll(pageable).stream()
				.map(this::toModel).toList();
	}

	private TransactionModel toModel(Transaction transaction) {
		return new TransactionModel(
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
	 * @param current the currently selected page
	 * @param page    the page size
	 * @param window  the window size around the selected page
	 * @return the list of pages
	 */
	public List<PageItem> getPagination(int current, int page, int window) {
		final long count = repository.count();
		final int pages = (int) Math.ceilDiv(count, page);

		var result = new ArrayList<PageItem>(3 + 2 * window);
		int i = 1;
		while (i <= pages) {
			if (i != 1 && i != pages) {
				if (i < current - window) {
					i = Math.min(current - window, pages);
					result.add(PageItem.GAP);
				} else if (i > current + window) {
					i = pages;
					result.add(PageItem.GAP);
				}
			}
			result.add(new PageItem(i, i == current, false));
			i++;
		}
		return List.copyOf(result);
	}
}
