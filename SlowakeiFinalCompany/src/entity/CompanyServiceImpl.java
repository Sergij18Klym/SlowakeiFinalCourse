package entity;

import interfaces.iCompanyService;

import java.util.List;

/**
 * Classname: CompanyServiceImpl
 *
 * @version     23 June 2020
 * @author      Klymenko Sergij, ONPU
 *
 * COMPANY Service implementation - 15 points
 *
 * IMPLEMENT THE FOLLOWING INTERFACE.
 *
 * 1. Create a test set of companies in your main class.
 * 2. Take for this set  IT-companies: Chief, Lawyer, Developer (Design,  Front-end, Back-end, DevOps). See attch.
 * 3. Take into account 4 different cases for a company (on-top, on bottom, in the node, out of chain)
 * 4. Test all this cases in your main class.
 * 5. Implement the interface in a class CompanyService.
 *
 * */

public class CompanyServiceImpl implements iCompanyService {

    /**
     * @param child - company for which we are searching the top level parent
     *                  (parent of parent of ...)
     * @return top level parent
     */
    @Override
    public Company getTopLevelParent(Company child) {
        /**
         * in case department has no parent method returns itself - a top level parent
         */
        if (child.getParent() == null) {
            return child;
        } else {
            return child.getParent();
        }
    }

    /**
     *
     * @param company  - company for which we are searching the count of employees
     *                 (count of this company employees +
     *                 count employees of all children and their children employees )
     * @param companies  - list of all available companies
     *
     * @return count of employees
     */
    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {

        long count = company.getEmployeesCount();

        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getParent() == company) {
                count += getEmployeeCountForCompanyAndChildren(companies.get(i), companies);
            }
        }

        return count;
    }
}
