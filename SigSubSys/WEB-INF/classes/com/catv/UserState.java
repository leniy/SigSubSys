package com.catv;

public enum UserState
{
    //�����Ѿ��ύ��ϢΪ��done, ����Ϊ��NOT_DONE; �Ժ���ܻ���� ���ڵ�״̬
	DONE("<span style='color:green;text-align:center;font-weight:bold;'>���ύ</span>"), NOT_DONE("<span style='color:red;text-align:center;font-weight:bold;'>δ�ύ</pan>");	
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