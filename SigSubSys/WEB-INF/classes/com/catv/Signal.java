package com.catv;

public class Signal
{
	private int ch_no;
	private String ch_freg;
	private float signal_video;
	private float signal_mer;
	private float signal_ber;
	private long submit_time;
	private int emp_id;
	private int week;
	private int year;
	
	Signal()
	{
	
	}
	
	Signal(int ch_no, String ch_freg, float signal_video, float signal_mer, float signal_ber, long submit_time, int emp_id, int week, int year)
	{
		this.ch_no = ch_no;
		this.ch_freg = ch_freg;
		this.signal_video = signal_video;
		this.signal_mer = signal_mer;
		this.signal_ber = signal_ber;
		this.submit_time = submit_time;
		this.emp_id = emp_id;
		this.week = week;
		this.year = year;
	}
	
	public int getCh_no()
	{
		return ch_no;
	}
	
	public void setCh_no(int ch_no)
	{
		this.ch_no = ch_no;
	}
	
	public String getCh_freg()
	{
		return ch_freg;
	}
	
	public void setCh_freg(String ch_freg)
	{
		this.ch_freg = ch_freg;
	}
	
	public float getSignal_video()
	{
		return signal_video;
	}
	
	public void setSignal_video(float  Signal_video)
	{
		this.signal_video = signal_video;
	}
	
	public float getSignal_mer()
	{
		return signal_mer;
	}
	
	public void setSignal_mer(float  signal_mer)
	{
		this.signal_mer = signal_mer;
	}
	
	public float getSignal_ber()
	{
		return signal_ber;
	}
	
	public void setSignal_ber(float  signal_ber)
	{
		this.signal_ber = signal_ber;
	}
	
	public long getSubmit_time()
	{
		return submit_time;
	}
	
	public void setSubmit_time(long  submit_time)
	{
		this.submit_time = submit_time;
	}
	
	public int getEmp_id()
	{
		return emp_id;
	}
	
	public void setEmp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}
	
	public int getWeek()
	{
		return week;
	}
	
	public void setWeek(int week)
	{
		this.week = week;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
}
