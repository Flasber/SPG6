package controller;

import exceptionHandling.*;
import model.BusinessCustomer;
import model.PrivateCustomer;
import model.Customer;
import model.CustomerContainer;

public class CustomerController {

	public Customer findCustomer(String tlf) {
		CustomerContainer customerContainer = CustomerContainer.getInstance();

		return customerContainer.findCustomer(tlf);

	}

	public Customer createCustomer(String name, String tlf, String cvr, String ean, String email) throws Exception{
		Customer c = null;
		if (findCustomer(tlf) != null){
			throw new PhoneNumberInUseException("Phone number is already in use");
		}
			else if (email == null){
				c = new BusinessCustomer(name, tlf, cvr, ean);
		}	else if (cvr == null && ean == null){
				c = new PrivateCustomer(name, tlf, email);
		}

		if (c != null){
			CustomerContainer customerContainer = CustomerContainer.getInstance();
			customerContainer.addCustomer(c);
		}
		return c;
	}

	public void updateCustomer(Customer c, String name, String tlf, String cvr, String ean, String email) throws EmailInUseException, PhoneNumberInUseException, CvrInUseException, EanInUseException{
		if (c.getTlf() == tlf){
			throw new PhoneNumberInUseException("Phone number is already in use");
		}
		if (c instanceof PrivateCustomer && ((PrivateCustomer) c).getEmail() == email){
			throw new EmailInUseException("Email is already in use");
		}
		if (c instanceof BusinessCustomer && ((BusinessCustomer) c).getCvr() == cvr){
			throw new CvrInUseException("Cvr is already in use");
		}
		if (c instanceof BusinessCustomer && ((BusinessCustomer) c).getEan() == ean){
			throw new EanInUseException("Ean is already in use");
		}
		c.setName(name);
		c.setTlf(tlf);
		if(c instanceof PrivateCustomer){
			((PrivateCustomer) c).setEmail(email);
		}
		if (c instanceof BusinessCustomer){
			((BusinessCustomer)c).setCvr(cvr);
			((BusinessCustomer)c).setEan(ean);
		}
	}

}
