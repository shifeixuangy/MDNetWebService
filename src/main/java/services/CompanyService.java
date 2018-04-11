package services;

import commonUtil.CommonVariable;
import data.common.TbCompany;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;

import java.util.List;

/**
 * Created by liudongdong on 2016/3/15.
 */
public class CompanyService {
    public List<TbCompany>getCompanyList(int companyID)
    {
        return CommonQueryHelper.getCompanies(companyID);
    }

    public int addCompany(String companyName)
    {
        TbCompany c=new TbCompany();
        c.setCompanyName(companyName);
        return DataHelper.add(c, CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }

    public void updateCompany(TbCompany c)
    {
        DataHelper.update(c,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }

    public void deleteCompany(int companyID)
    {
        TbCompany c=new TbCompany();
        c.setId(companyID);
        DataHelper.delete(c,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }
}
