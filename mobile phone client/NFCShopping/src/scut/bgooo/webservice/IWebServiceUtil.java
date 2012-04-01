package scut.bgooo.webservice;

import java.util.List;

import scut.bgooo.entities.Review;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.Paging;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.User;

public interface IWebServiceUtil {
	
	/**
	 * <p>
	 * 登录判断接口
	 * 
	 * @return 返回User对象
	 * */
	public User login(String userName, String password);
	
	public User regist(String userName,String password,int gencer);

	/**
	 * <p>
	 * 通过用户的id获取到用户的信息
	 * <p>
	 * 用在通过评论的时候查看用户的信息
	 * 
	 * @param id
	 *            用户的id
	 * @return 返回User对象
	 * */
	public User getUser(int id);

	/**
	 * <p>
	 * 获取最新的20条优惠信息接口
	 * 
	 * @return 优惠信息列表
	 * 
	 * */
	public List<Discount> getDiscounts();

	/**
	 * <p>
	 * 根据传入的分页对象获取优惠信息接口
	 * 
	 * @return 优惠信息列表
	 * */
	public List<Discount> getDiscounts(Paging page);
	
	

	/**
	 * <p>
	 * 根据优惠的id获取优惠的详细信息
	 * 
	 * @param id
	 *            优惠id
	 * @return 优惠信息
	 * */
	public Discount getDiscount(int id);

	/**
	 * <p>
	 * 通过商品id获取商品对象
	 * 
	 * @param id
	 *            商品的barcode 条形码编号
	 * 
	 * @return 返回商品的对象
	 * 
	 * */
	public Product getProductById(String barcode);

	/**
	 * <p>
	 * 获取我最新的20条评论列表
	 * 
	 * @return 返回评论列表
	 * 
	 * */
	public List<Review> getCommentsByMe();

	/**
	 * <p>
	 * 获取根据Paging获取评论列表
	 * 
	 * @param Paging
	 *            Paging 对象
	 * @return 返回评论列表
	 * 
	 * */
	public List<Review> getCommentsByMe(Paging page);

	/**
	 * 
	 * <p>
	 * 通过商品id获取商品的最新20条评论
	 * 
	 * @param id
	 *            商品的barcode 条形码编号
	 * @return 评论列表
	 */
	public List<Review> getComments(int id);

	/**
	 * 
	 * <p>
	 * 通过商品id和Paging对象获取商品的评论
	 * 
	 * @param barcode
	 *            商品的barcode 条形码编号
	 * @param page
	 *            Paging 分页对象
	 * @return 评论列表
	 */
	public List<Review> getComments(String barcode, Paging page);

	/**
	 * 
	 * <p>
	 * 通过商品的id获取商品的属性
	 * 
	 * @param id
	 *            商品的barcode 条形码编号
	 * @param page
	 *            Paging 分页对象
	 * @return 评论列表
	 */
	public List<String> getAttributes(String barcode);

}
