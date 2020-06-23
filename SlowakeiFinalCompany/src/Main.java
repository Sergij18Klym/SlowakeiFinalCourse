import entity.Company;
import entity.CompanyServiceImpl;
import interfaces.iCompanyService;

import java.util.Arrays;
import java.util.List;

/**
 * Classname: Main
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

public class Main {

    public static void main(String[] args) {

        iCompanyService service = new CompanyServiceImpl();

        Company chief = new Company("chief",null, 1);
        Company lawyer = new Company("lawyer",chief, 2);
        Company developer = new Company("developer",chief, 18);
        Company backEnd = new Company("back-end",developer, 8);
        Company frontEnd = new Company("front-end",developer, 10);
        Company devOps = new Company("devOps",backEnd, 8);
        Company design = new Company("design",frontEnd, 10);
        Company accounting = new Company("accounting",null, 6);

        List<Company> companies = Arrays.asList(
                chief, lawyer, developer, backEnd, frontEnd, devOps, design, accounting
        );

        String chiefTop = service.getTopLevelParent(chief).getName();
        String lawyerTop = service.getTopLevelParent(lawyer).getName();
        String developerTop = service.getTopLevelParent(developer).getName();
        String backEndTop = service.getTopLevelParent(backEnd).getName();
        String frontEndTop = service.getTopLevelParent(frontEnd).getName();
        String devOpsTop = service.getTopLevelParent(devOps).getName();
        String designTop = service.getTopLevelParent(design).getName();
        String accountingTop = service.getTopLevelParent(accounting).getName();

        long chiefCount = service.getEmployeeCountForCompanyAndChildren(chief, companies);
        long lawyerCount = service.getEmployeeCountForCompanyAndChildren(lawyer, companies);
        long developerCount = service.getEmployeeCountForCompanyAndChildren(developer, companies);
        long backEndCount = service.getEmployeeCountForCompanyAndChildren(backEnd, companies);
        long frontEndCount = service.getEmployeeCountForCompanyAndChildren(frontEnd, companies);
        long devOpsCount = service.getEmployeeCountForCompanyAndChildren(devOps, companies);
        long designCount = service.getEmployeeCountForCompanyAndChildren(design, companies);
        long accountingCount = service.getEmployeeCountForCompanyAndChildren(accounting, companies);

        System.out.println("The top level parent of Chief department is " + chiefTop + " (himself)");
        System.out.println("The top level parent of Lawyer department is " + lawyerTop);
        System.out.println("The top level parent of Developer department is " + developerTop);
        System.out.println("The top level parent of BackEnd department is " + backEndTop);
        System.out.println("The top level parent of FrontEnd department is " + frontEndTop);
        System.out.println("The top level parent of DevOps department is " + devOpsTop);
        System.out.println("The top level parent of Design department is " + designTop);
        System.out.println("The top level parent of Accounting department is " + accountingTop + " (itself)\n");

        System.out.println("Chief has " + chiefCount + " employees.");
        System.out.println("Lawyer department enlists " + lawyerCount + " employees.");
        System.out.println("Developer department enlists " + developerCount + " employees.");
        System.out.println("BackEns department enlists " + backEndCount + " employees.");
        System.out.println("FrontEnd department enlists " + frontEndCount + " employees.");
        System.out.println("DevOps department enlists " + devOpsCount + " employees.");
        System.out.println("Design department enlists " + designCount + " employees.");
        System.out.println("Accounting department enlists " + accountingCount + " employees.");

    }
}
