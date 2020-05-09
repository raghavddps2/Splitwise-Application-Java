package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.MoneyStatus;
import com.test.beans.Intermediate;
import com.test.dao.ApplicationDao;
import com.test.beans.CashFlowDisplay;

/**
 * Servlet implementation class SettleUp
 */
@WebServlet("/settleUp")
public class SettleUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettleUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public ArrayList<CashFlowDisplay> settleCash(ArrayList<MoneyStatus> status){
    	
    	ArrayList<Intermediate> shouldBePaid = new ArrayList<Intermediate>();
    	ArrayList<Intermediate> shouldPay = new ArrayList<Intermediate>();
    	
    	for(MoneyStatus moneyStatus: status) {
    		Intermediate imd = new Intermediate();
    		imd.setMember(moneyStatus.getMember_name());
    		if(moneyStatus.getMoney_lend() < moneyStatus.getMoney_owed()) {
    			imd.setMoney(moneyStatus.getMoney_owed()-moneyStatus.getMoney_lend());
    			shouldPay.add(imd);
    		}
    		else if(moneyStatus.getMoney_lend() > moneyStatus.getMoney_owed()){
    			imd.setMoney(moneyStatus.getMoney_lend()-moneyStatus.getMoney_owed());
    			shouldBePaid.add(imd);
    		}
    	}
    	ArrayList<CashFlowDisplay> arr = new ArrayList<CashFlowDisplay>();
    	for(Intermediate shouldBePaidRef: shouldBePaid) {
    		for(Intermediate shouldPayRef: shouldPay) {
    			double moneyToBepaid = shouldBePaidRef.getMoney();
    			double moneyToPay = shouldPayRef.getMoney();
    			if(moneyToBepaid > moneyToPay) {
    				CashFlowDisplay temp = new CashFlowDisplay();
    				temp.setMoney(moneyToPay);
    				temp.setPay_by(shouldPayRef.getMember());
    				temp.setPay_to(shouldBePaidRef.getMember());
    				shouldPayRef.setMoney(0);
    				shouldBePaidRef.setMoney(moneyToBepaid-moneyToPay);
    				arr.add(temp);
    			}
    			else {
    				CashFlowDisplay temp = new CashFlowDisplay();
    				temp.setMoney(moneyToBepaid);
    				temp.setPay_by(shouldPayRef.getMember());
    				temp.setPay_to(shouldBePaidRef.getMember());
    				shouldPayRef.setMoney(moneyToPay-moneyToBepaid);
    				shouldBePaidRef.setMoney(0);
    				arr.add(temp);
    			}
    		}
    	}
    	return arr;
    	
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession(false) == null) {
			request.setAttribute("message","Login or register first");
			request.getRequestDispatcher("login").forward(request, response);
		}
		
		ApplicationDao dao = new ApplicationDao();
		String groupId = request.getParameter("groupId");
		String groupOwner = request.getParameter("groupOwner");
		ArrayList<MoneyStatus> status = dao.getMoneyStatus(groupId);
		ArrayList<CashFlowDisplay> ans = settleCash(status);
		request.setAttribute("settlement",ans);
		System.out.print(ans.size()+"**");
		for(CashFlowDisplay i:ans) {
			System.out.print(i.getMoney());
		}
		request.setAttribute("groupId",groupId);
		request.setAttribute("groupOwner",groupOwner);
		request.getRequestDispatcher("html/settleUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String groupId = (String)request.getParameter("groupId");
		System.out.print("zolo"+groupId);
		ApplicationDao dao = new ApplicationDao();
		int ans = dao.settleUp(groupId);
		if(ans == 0) {
			request.setAttribute("message","Some error occured");
		}
		else {
			request.setAttribute("message","All you bills are setllled up now. Thank you for using settle up!");
		}

		request.getRequestDispatcher("profile").forward(request, response);
	}

}
