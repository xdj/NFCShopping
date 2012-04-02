package scut.bgooo.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import scut.bgooo.entities.Review;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.Paging;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.Suggestion;
import scut.bgooo.entities.User;

public class WebServiceUtil implements IWebServiceUtil {

	private static String TAG = WebServiceUtil.class.getName();

	private static final String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://10.0.2.2:56989/ShopWebService.asmx";

	private static final String REGIST = "Regist";
	private static final String ADDSUGGESTION = "AddSuggestion";
	private static final String ADDREVIEW = "AddReview";
	private static final String GETUSER = "GetUser";
	private static final String LOGIN = "Login";
	private static final String GETREVIEWSBYUSERID = "FindReviewsByUserID";
	private static final String GETREVIEWSBYPRODUCTID = "FindReviewsByProductID";
	private static final String GETPRODUCTBYBARCODE = "FindProductByBarcode";
//	private static final String GETUSERBYID = "FindUserByID";  //暂时可以不用到
	public static User nowUser = null;
	private static WebServiceUtil Webservice=new WebServiceUtil();
	/**
	 * 装逼的用了一下单例模式
	 * 
	 * */
	public static WebServiceUtil getInstance()
	{
		return Webservice;
	}

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		SoapObject rpc = getSoapObject(Method.LOGIN);
		rpc.addProperty("userName", userName);
		rpc.addProperty("password", password);
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;// 注意：这个属性是对dotnetwebservice协议的支持,如果dotnet的webservice
								// 不指定rpc方式则用true否则要用false
		envelope.setOutputSoapObject(rpc);
		/**
		 * 对象的返回值
		 * 
		 * */
		nowUser = null;
		try {
			ht.call(NAMESPACE + LOGIN, envelope);
			if (!envelope.getResponse().equals(null))
				nowUser = (User) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 返回的是vector对象
	 * */
	public Vector<Review> getReviewsByMe() {
		// TODO Auto-generated method stub
		if (nowUser.equals(null)) {
			return null; // 用户为空的时候，返回null
		} else {
			SoapObject rpc = getSoapObject(Method.GETREVIEWSBYUSERID);
			rpc.addProperty("uid", nowUser.getProperty(0)); // 传递用户的id
			HttpTransportSE ht = new HttpTransportSE(URL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = false;
			envelope.setOutputSoapObject(rpc);
			Vector<Review> result = null;
			try {
				ht.call(NAMESPACE + GETREVIEWSBYUSERID, envelope);
				result = (Vector<Review>) envelope.getResponse();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result; // 获取不到评论的时候， vector的size 是 0
		}
	}

	@Override
	public Vector<Review> getReviewsByMe(Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<Review> getReviewsByProductId(int id) {
		// TODO Auto-generated method stub
		SoapObject rpc = getSoapObject(Method.GETREVIEWSBYPRODUCTID);
		rpc.addProperty("pid", id); // 传递商品的id
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;
		envelope.setOutputSoapObject(rpc);
		Vector<Review> result = null;
		try {
			ht.call(NAMESPACE + GETREVIEWSBYPRODUCTID, envelope);
			result = (Vector<Review>) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Review> getReviewsByProductId(int id, Paging page) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User getUser(int id) {// 好像没什么必要了，因为获取商品的时候会顺带把用户的信息下载下来
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discount> getDiscounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discount> getDiscounts(Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Discount getDiscount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByBarcode(String barcode) {
		// TODO Auto-generated method stub
		SoapObject rpc = getSoapObject(Method.GETPRODUCTBYBARCODE);
		rpc.addProperty("barcode", barcode); // 传递用户的id
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;
		envelope.setOutputSoapObject(rpc);
		Product result = null;
		try {
			ht.call(NAMESPACE + GETPRODUCTBYBARCODE, envelope);
			if (!envelope.getResponse().equals(null)) // 判断返回的对象是否为空
				result = (Product) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 返回null 表示找不到对象
	}

	@Override
	public List<String> getAttributes(String barcode) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public User regist(String userName, String password, int gender) {
		// TODO Auto-generated method stub
		SoapObject rpc = getSoapObject(Method.REGIST);
		rpc.addProperty("userName", userName);
		rpc.addProperty("password", password);
		rpc.addProperty("gender", gender);
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;// 注意：这个属性是对dotnetwebservice协议的支持,如果dotnet的webservice
								// 不指定rpc方式则用true否则要用false
		envelope.setOutputSoapObject(rpc);
		// envelope.addMapping(NAMESPACE, "User", User.class);//
		// 传对象时必须，参数namespace是webservice中指定的，
		// name是服务器类型的名称，
		// claszz是自定义类的类型
		/**
		 * 对象的返回值
		 * */
		nowUser = null; // 完成注册返回的对象
		try {
			ht.call(NAMESPACE + REGIST, envelope);
			nowUser = (User) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowUser;
	}

	@Override
	public boolean AddReview(Review review) {
		// TODO Auto-generated method stub
		SoapObject rpc=getSoapObject(Method.ADDREVIEW);
		PropertyInfo pi=getPropertyInfo(review);
		rpc.addProperty(pi);
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;
		envelope.setOutputSoapObject(rpc);
		envelope.addMapping(NAMESPACE, "Review", review.getClass());
		try {
			ht.call(NAMESPACE+ADDREVIEW, envelope);
			if(!envelope.getResponse().equals(null)){
				return (Boolean)envelope.getResponse();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddSuggestion(Suggestion suggestion) {
		// TODO Auto-generated method stub
		SoapObject rpc=getSoapObject(Method.ADDSUGGESTION);
		PropertyInfo pi=getPropertyInfo(suggestion);
		rpc.addProperty(pi);
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;
		envelope.setOutputSoapObject(rpc);
		envelope.addMapping(NAMESPACE, "Suggestion", suggestion.getClass());
		try {
			ht.call(NAMESPACE+ADDSUGGESTION, envelope);
			if(!envelope.getResponse().equals(null)){
				return (Boolean)envelope.getResponse();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private PropertyInfo getPropertyInfo(Object object) {
		PropertyInfo pi = new PropertyInfo();
		if (object instanceof Review) {
			pi.setName("review");
			pi.setValue((Review) object);
			pi.setType(((Review) object).getClass());
		} else if (object instanceof Suggestion) {
			pi.setName("suggestion");
			pi.setValue((Suggestion) object);
			pi.setType(((Suggestion) object).getClass());
		}
		return pi;
	}

	private SoapObject getSoapObject(int methodID) {
		SoapObject rpc = null;
		switch (methodID) {
		case Method.REGIST:
			rpc = new SoapObject(NAMESPACE, REGIST);
			break;
		case Method.LOGIN:
			rpc = new SoapObject(NAMESPACE, LOGIN);
			break;
		case Method.ADDSUGGESTION:
			rpc = new SoapObject(NAMESPACE, ADDSUGGESTION);
			break;
		case Method.ADDREVIEW:
			rpc = new SoapObject(NAMESPACE, ADDREVIEW);
			break;
		case Method.GETUSER:
			rpc = new SoapObject(NAMESPACE, GETUSER);
			break;
		}

		return rpc;
	}
}
