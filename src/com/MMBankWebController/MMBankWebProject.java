package com.MMBankWebController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.exception.AccountNotFoundException;

/**
 * Servlet implementation class MMBankWebProject
 */
@WebServlet("*.mm")
public class MMBankWebProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = false;

	public MMBankWebProject() {
		super();

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bankapp_db", "root", "root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM ACCOUNT");
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl();
		String path = request.getServletPath();
		PrintWriter out = response.getWriter();                                        
		RequestDispatcher dispatcher;
		System.out.println(path);
		SavingsAccount savingsAccount = null, savingsAccountOne = null;

		int accountNumber = 0;
		switch (path) {
		case "/addNewAccountForm.mm":
			response.sendRedirect("addNewSavingsAccount.jsp");
			break;
		case "/addnewSavingsAccount.mm":
			String accountHolderName = request.getParameter("account_hn");
			double accountBalance = Double.parseDouble(request
					.getParameter("accountbalance"));
			boolean accountType = request.getParameter("salary")
					.equalsIgnoreCase("yes") ? false : true;
			try {
				savingsAccountService.createNewAccount(accountHolderName,
						accountBalance, accountType);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "/checkAccountBalanceForm.mm":
			response.sendRedirect("checkCurrentBalance.jsp");
			break;
		case "/checkingCurentBalance.mm":
			int accountNumber1 = Integer.parseInt(request
					.getParameter("accountnumber"));
			try {
				savingsAccountService.checkBalance(accountNumber1);
				out.println(savingsAccountService.checkBalance(accountNumber1));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/deleteAccountForm.mm":
			response.sendRedirect("deleteAccount.jsp");
			break;

		case "/deleteAccount.mm":
			int accountNumber2 = Integer.parseInt(request
					.getParameter("accountnumber"));
			try {
				savingsAccountService.deleteAccount(accountNumber2);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/withdrawForm.mm":
			response.sendRedirect("Withdraw.jsp");
			break;
		case "/withdrawBalance.mm":
			int accountNumberToWithdraw = Integer.parseInt(request
					.getParameter("accountnumber"));
			double amountToWithdraw = Double.parseDouble(request
					.getParameter("amount"));
			try {
				savingsAccount = savingsAccountService
						.getAccountById(accountNumberToWithdraw);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				savingsAccountService
						.withdraw(savingsAccount, amountToWithdraw);
				DBUtil.commit();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "/depositForm.mm":
			response.sendRedirect("Deposit.jsp");
			break;
		case "/depositMoney.mm":
			int accountNumberToDeposit = Integer.parseInt(request
					.getParameter("accountnumber"));
			double amountToDeposit = Double.parseDouble(request
					.getParameter("amount"));
			try {
				savingsAccount = savingsAccountService
						.getAccountById(accountNumberToDeposit);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				savingsAccountService.deposit(savingsAccount, amountToDeposit);
				DBUtil.commit();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "/transferForm.mm":
			response.sendRedirect("TransferMoneyForm.jsp");
			break;
		case "/transferMoney.mm":
			int sendersAccountNumber = Integer.parseInt(request
					.getParameter("senderaccountnumber"));
			int receiversAccountNumber = Integer.parseInt(request
					.getParameter("receiveraccountnumber"));
			double amountToTransfer = Double.parseDouble(request
					.getParameter("amount"));
			try {
				savingsAccount = savingsAccountService
						.getAccountById(sendersAccountNumber);
				savingsAccountOne = savingsAccountService
						.getAccountById(receiversAccountNumber);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				savingsAccountService.fundTransfer(savingsAccount,
						savingsAccountOne, amountToTransfer);
				DBUtil.commit();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/searchForm.mm":
			response.sendRedirect("SearchForm.jsp");
			break;
		case "/search.mm":
			int accountNumber11 = Integer.parseInt(request
					.getParameter("txtAccountNumber"));
			try {
				SavingsAccount account = savingsAccountService
						.getAccountById(accountNumber11);
				request.setAttribute("account", account);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "/getAll.mm":
			try {
				List<SavingsAccount> accounts = savingsAccountService
						.getAllSavingsAccount();
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/sortByName.mm":
			flag = !flag;
			try {
				Collection<SavingsAccount> accounts = savingsAccountService
						.getAllSavingsAccount();
				Set<SavingsAccount> accountSet = new TreeSet<>(
						new Comparator<SavingsAccount>() {
							@Override
							public int compare(SavingsAccount arg0,
									SavingsAccount arg1) {
								int result = arg0
										.getBankAccount()
										.getAccountHolderName()
										.compareTo(
												arg1.getBankAccount()
														.getAccountHolderName());
								if (flag == true) {
									return result;
								} else {
									return -result;
								}
							}
						});
				accountSet.addAll(accounts);
				request.setAttribute("accounts", accountSet);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/sortByBalance.mm":
			flag = !flag;
			try {
				Collection<SavingsAccount> accounts = savingsAccountService
						.getAllSavingsAccount();
				Set<SavingsAccount> accountSet = new TreeSet<>(
						new Comparator<SavingsAccount>() {
							@Override
							public int compare(SavingsAccount arg0,
									SavingsAccount arg1) {
								int result = (int) (arg0.getBankAccount()
										.getAccountBalance() - (arg1
										.getBankAccount().getAccountBalance()));
								if (flag == true) {
									return result;
								} else {
									return -result;
								}
							}
						});
				accountSet.addAll(accounts);
				request.setAttribute("accounts", accountSet);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/updateAccountForm.mm":
			response.sendRedirect("updateForm.jsp");
			break;
		case "/updateAccount.mm":
			int accNumber = Integer.parseInt(request
					.getParameter("accountnumber"));
			try {
				SavingsAccount accountUpdate = savingsAccountService
						.getAccountById(accNumber);
				request.setAttribute("accounts", accountUpdate);
				dispatcher = request.getRequestDispatcher("updateAccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case
			"/updateAccountDetails.mm":
				int accountId = Integer.parseInt(request.getParameter("accNo"));
				SavingsAccount accountUpdate;
				try {
					accountUpdate = savingsAccountService.getAccountById(accountId);
					String accHolderName = request.getParameter("accHolderName");
					accountUpdate.getBankAccount().setAccountHolderName(accHolderName);
					double accBal = Double.parseDouble(request.getParameter("accBalance"));
					boolean isSalary = request.getParameter("rdSal").equalsIgnoreCase("no")?false:true;
					accountUpdate.setSalary(isSalary);
					savingsAccountService.updateAccount(accountUpdate);
					response.sendRedirect("getAll.mm");
				} catch (ClassNotFoundException | SQLException
						| AccountNotFoundException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
