package hu.wodster.blogster.common.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageWrapper<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1221566691175726598L;

	public static final int MAX_PAGE_ITEM_DISPLAY = 5;
	private final Page<T> page;
	private final List<PageItem> items;
	private final int currentNumber;

	public PageWrapper(final Page<T> page) {
		this.page = page;
		items = new ArrayList<PageItem>();

		currentNumber = page.getNumber() + 1; // start from 1 to match page.page

		int start, size;
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 1;
			size = page.getTotalPages();
		} else {
			if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else {
				start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
				size = MAX_PAGE_ITEM_DISPLAY;
			}
		}

		for (int i = 0; i < size; i++) {
			items.add(new PageItem(start + i, (start + i) == currentNumber));
		}
	}

	public List<PageItem> getItems() {
		return items;
	}

	public int getNumber() {
		return currentNumber;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotal() {
		return page.getTotalPages();
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public class PageItem {
		private final int number;
		private final boolean current;

		public PageItem(final int number, final boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return this.number;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}
}
