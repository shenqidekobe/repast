package repast.yiyou.common.base;

/**
 * 枚举定义中心
 * */
public class EnumDefinition {
	
    //商户状态
	public static enum MerchantStaus {
		normal("正常"),
	    disable("已禁用");
	    private String name;
	    private MerchantStaus(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//账户类型
	public enum AccountType {
		terminal("终端接单"),
	    manager("后端管理");
	    private String name;
	    private AccountType(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//账户状态
	public enum AccountStaus {
		normal("正常"),
	    disable("已禁用");
	    private String name;
	    private AccountStaus(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//申请审核状态
	public enum AuthorizeAuditStaus {
		await("待审核"),
	    pass("同意"),
	    use("已使用"),
	    refuse("拒绝");
	    private String name;
	    private AuthorizeAuditStaus(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//用户状态
	public enum UserWhiteStaus {
		normal("正常"),
	    disable("已禁用");
	    private String name;
	    private UserWhiteStaus(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//用户类型
	public enum UserWhiteType {
		senior("高管"),
	    common("普通");
	    private String name;
	    private UserWhiteType(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}

	
	//订单状态
	public enum OrderStaus {
		await("已下单"),
	    ing("出菜中"),
	    already("已出菜"),
	    awaitPay("待付款"),
	    settle("已结算"),
	    cancel("已取消");
	    private String name;
	    private OrderStaus(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	

	//支付方式
	public enum PayWay {
		senior("高管点餐"),
	    card("充值卡消费"),
	    authorize("授权消费");
	    private String name;
	    private PayWay(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return this.name;
	    }
	}
	
	//结算方式
	public enum SettleWay {
		inside("内部人员结算"),
		outside("外部人员结算");
		private String name;
		private SettleWay(String name){
	        this.name = name;
	    }
		public String getName() {
			return this.name;
		}
	}
	
	//订单处理状态
	public enum OrderProcessStatus {
		await("待处理"),
		process("已处理");
		private String name;
		private OrderProcessStatus(String name){
	        this.name = name;
	    }
		public String getName() {
			return this.name;
		}
	}
	

}
