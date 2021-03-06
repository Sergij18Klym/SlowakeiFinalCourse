package entity;

import interfaces.iCompanyService;

import java.util.List;
import java.util.stream.Collectors;

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

        /**
         * counting employees of its own department
         */
        long generalCount = company.getEmployeesCount();

        /**
         * Filtering all the department's children departments
         * and count employees foreach of them
         */
        List<Company> children = companies.stream()
                .filter(companie -> company.equals(companie.getParent()))
                .collect(Collectors.toList());

        long childrenCount = 0;

        for (Company child:children) {
            childrenCount += getEmployeeCountForCompanyAndChildren(child, companies);
        }

        /**
         * counting the complete hierarchy employees
         */
        long countAll = generalCount + childrenCount;

        return countAll;
    }
}
