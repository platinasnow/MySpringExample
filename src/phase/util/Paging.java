package phase.util;

public class Paging {

	private int itemPerPage = 10;
	private int nextPage = 10;
	private int page = 1;
	private int totalItemCount;
	
	public Paging(int totalItemCount, int itemPerPage, int nextPage ){
		this.totalItemCount = totalItemCount;
		this.nextPage = nextPage;
		this.itemPerPage = itemPerPage;
	}

	public int getCurrentPage() {
		int page = this.page;
		if (page < 1) {
			page = 1;
		}
		int pageCount = getPageCount();
		if (page > pageCount) {
			page = pageCount;
		}
		return page;
	}
	
	public int getPageCount() {
		return (totalItemCount - 1) / itemPerPage + 1;
	}
	
	public int getPageBegin(){
		return ((getCurrentPage() - 1) / itemPerPage ) * itemPerPage +1;
	}
	
	public int getPageEnd(){
		int pageCount = getPageCount();
		int num= getPageBegin() + nextPage - 1;
		return Math.min(pageCount, num);
	}
	
	public int getItemSeqBegin(){
		int page = this.page;
		int itemPerPage = this.itemPerPage;
		return (page - 1) * itemPerPage +1 ;
	}
	
	public int getItemSeqEnd(){
		int page = this.page;
		int itemPerPage = this.itemPerPage;
		int totalItemCount = this.totalItemCount;
		if(totalItemCount == 0){
			return page * itemPerPage;
		}else{
			return Math.min(totalItemCount, page*itemPerPage);
		}
		
	}
	
	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
	
}
