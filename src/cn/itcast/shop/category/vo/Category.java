package cn.itcast.shop.category.vo;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

/**
 * Ò»ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
 * @author M MMMM
 *
 */
public class Category implements Serializable{

	private int cid;
	private String cname;
	//¶þ¼¶·ÖÀà
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
