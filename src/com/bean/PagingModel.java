package com.bean;

public class PagingModel {
	private int CurrentP;			//��ǰҳ��
	private int AllP;				//��ҳ��
	private int AllR;				//�ܼ�¼��
	private int PerR;				//ÿҳ��ʾ��¼��
	private String pageLink;		//��ҳ��������Ϣ
	private String pageInfo;		//��ҳ״̬��ʾ��Ϣ
	
	public PagingModel(){
		CurrentP=1;
		AllP=1;
		AllR=0;
		PerR=3;
		pageLink="";
		pageInfo="";
	}
	
	/** ����ÿҳ��ʾ��¼�� */
	public void setPerR(int PerR){
		this.PerR=PerR;
	}
	
	/** �����ܼ�¼�� */
	public void setAllR(int AllR){
		this.AllR=AllR;
	}
	/** ������ҳ�� */
	public void setAllP(){
		AllP=(AllR%PerR==0)?(AllR/PerR):(AllR/PerR+1);
	}
	
	/** ���õ�ǰҳ�� */
	public void setCurrentP(String currentP) {		
		if(CurrentP<1)
			CurrentP=1;
		if(CurrentP>AllP)
			CurrentP=AllP;	
		CurrentP = Integer.parseInt(currentP);
	}

	/** ���÷�ҳ״̬��ʾ��Ϣ */
	public void setPageInfo(){
		if(AllP>1){
			pageInfo="<table border='0' cellpadding='3' ><tr><td style='width:100%;'>";
			pageInfo+="ÿҳ��ʾ��"+PerR+"/"+AllR+" ����¼��";
			pageInfo+="��ǰҳ��"+CurrentP+"/"+AllP+" ҳ��";
			pageInfo+="</td></tr></table>";			
		}				
	}
	
    /** ���÷�ҳ��������Ϣ */
	public void setPageLink(String gowhich){
		if(gowhich==null)
			gowhich="";
		if(gowhich.indexOf("?")>=0)
			gowhich+="&";
		else
			gowhich+="?";
		if(AllP>1){
			pageLink="<table border='0' cellpadding='3' ><tr><td style='width:100%;'>";
			if(CurrentP>1){
				pageLink+="<a href='"+gowhich+"showpage=1'>��ҳ</a>&nbsp;";
				pageLink+="<a href='"+gowhich+"showpage="+(CurrentP-1)+"'>��һҳ</a>&nbsp;";
			}
			if(CurrentP<AllP){
				pageLink+="<a href='"+gowhich+"showpage="+(CurrentP+1)+"'>��һҳ</a>&nbsp;";
				pageLink+="<a href='"+gowhich+"showpage="+AllP+"'>βҳ</a>";
			}
			pageLink+="</td></tr></table>";			
		}		
	}
	
	/** ����ÿҳ��¼�� */
	public int getPerR(){
		return PerR;
	}
	
	/** �����ܼ�¼�� */
	public int getAllR() {
		return AllR;
	}	
	
    /** ������ҳ�� */
	public int getAllP() {		
		return AllP;
	}

	/** ���ص�ǰҳ�� */
	public int getCurrentP() {
		return CurrentP;
	}
	
	/** ���ط�ҳ״̬��ʾ��Ϣ */
	public String getPageInfo(){
		return pageInfo;
	}

	/** ���ط�ҳ��������Ϣ */
	public String getPageLink(){
		return pageLink;
	}
}
