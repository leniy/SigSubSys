package com.catv;

public enum UserState
{
    //本周已经提交信息为：done, 否则为：NOT_DONE; 以后可能还添加 过期等状态
	DONE("<span style='color:green;text-align:center;font-weight:bold;'>已提交</span>"), NOT_DONE("<span style='color:red;text-align:center;font-weight:bold;'>未提交</pan>");	
	private String context;
	
	private UserState(String context)
	{
		this.context = context;
	}
	private String getContext()
	{
		return context;
	}
	 @Override
    public String toString() {

        return this.context;

    }
}