package com.sz.pojo.manage.page;

import com.sz.common.listener.SystemConfigUtil;

import java.util.List;

public final class PaginationResult<E> {

	private int total = 0;
	private List<E> resultList;
	private int pageSize;

	/**
	 * Constructor
	 *
	 * @param total
	 * @param result
	 */
	public PaginationResult(int total, List<E> result) {
		super();
		this.total = total;
		this.resultList = result;
		this.pageSize = Integer.parseInt(SystemConfigUtil.getProperty("admin.main.page.size"));
	}

	public PaginationResult(int total, List<E> resultList, int pageSize) {
		this.total = total;
		this.resultList = resultList;
		this.pageSize = pageSize;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @return the result
	 */
	public List<E> getResultList() {
		return resultList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
