package com.avaya.smgr.Links;
/*
 Agent Management UI Related Test case
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class ElementLinks{
	private static final String Sytem = null;
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod(alwaysRun=true)
public void setup() throws IOException, InterruptedException
{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);

}
/*
@Test(priority=1,description="Verify the links of Collaboration Environment Tab", groups={"Sanity"})
 
 public void Collaboration_Environment() throws Exception{
	try{
		action.RefreshPage();
		//Click on different Collaboration Environment Links and verify title of page
		action.ClickLink(locator.getProperty("Collaboration_Environment"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Server_Administration"));
		action.ClickLink(locator.getProperty("Server_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Server_Administration"));
		action.ClickLink(locator.getProperty("Cluster_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Cluster_Administration"));
		action.ClickLink(locator.getProperty("Service_Management"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Service_Management"));
		
		//Click on different Configuration Links and verify title of page
		action.ClickLink(locator.getProperty("Configuration"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Configuration"));
		action.VerifyTitle(locator.getProperty("Configuration"));
		action.ClickLink(locator.getProperty("Service_Profiles")); 
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Service_Profile_Configuration"));
		action.VerifyTitle(locator.getProperty("Service_Profile_Configuration"));
		
		action.ClickLink(locator.getProperty("Attributes"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Attributes_Configuration"));
		action.VerifyTitle(locator.getProperty("Attributes_Configuration"));
		action.ClickLink(locator.getProperty("Logging"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Logging"));
		action.ClickLink(locator.getProperty("Avaya_Media_Server"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Avaya_Media_Server_Configuration"));
		action.VerifyTitle(locator.getProperty("Avaya_Media_Server_Configuration"));
		action.ClickLink(locator.getProperty("Event_Catalog"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Event_Catalog_Configuration"));
		action.ClickLink(locator.getProperty("HTTP_Security"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("HTTP_Security"));
		action.ClickLink(locator.getProperty("Implicit_Users"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Implicit_Users"));
		
		//Click on different System Tools Links and verify title of page
		action.ClickLink(locator.getProperty("System_Tools"));	
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("System_Tools"));
		action.VerifyTitle(locator.getProperty("System_Tools"));
		action.ClickLink(locator.getProperty("SNMP_MIB"));	
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("SNMP_MIB_Download"));
		action.ClickLink(locator.getProperty("Maintenance_Tests"));	
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Maintenance_Tests"));
		action.VerifyTitle(locator.getProperty("Maintenance_Tests"));
		
		
	}
	catch(Exception e){}
}
*/

@Test(priority=1,description="Verify the links of Engagement Development Platform Links", groups={"Sanity"})

public void Engagement_Development_Platform() throws Exception{
	try{
		action.RefreshPage();
		
		//Click on different Engagement Development Platform Links and verify title of page
		action.ClickLink(locator.getProperty("Engagement_Development_Platform"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Cluster_Administration"));
		action.ClickLink(locator.getProperty("Server_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Server_Administration"));
		action.ClickLink(locator.getProperty("Cluster_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Cluster_Administration"));
		action.ClickLink(locator.getProperty("Service_Management"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Service_Management"));
				
		//Click on different Configuration Links and verify title of page
		action.ClickLink(locator.getProperty("Configuration"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Configuration"));
		action.VerifyTitle(locator.getProperty("Configuration"));
		action.ClickLink(locator.getProperty("Service_Profiles")); 
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Service_Profile_Configuration"));
		action.VerifyTitle(locator.getProperty("Service_Profile_Configuration"));
				
		action.ClickLink(locator.getProperty("Attributes"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Attributes_Configuration"));
		action.VerifyTitle(locator.getProperty("Attributes_Configuration"));
		action.ClickLink(locator.getProperty("Logging"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Logging"));
		action.ClickLink(locator.getProperty("Avaya_Media_Server"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Avaya_Media_Server_Configuration"));
		action.VerifyTitle(locator.getProperty("Avaya_Media_Server_Configuration"));
		action.ClickLink(locator.getProperty("Event_Catalog"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Event_Catalog_Configuration"));
		action.ClickLink(locator.getProperty("HTTP_Security"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("HTTP_Security"));
		action.ClickLink(locator.getProperty("Implicit_Users"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Implicit_Users"));
		action.ClickLink(locator.getProperty("JDBC_Provider"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("JDBC_Provider"));
		action.ClickLink(locator.getProperty("JDBC_Source"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("JDBC_Data_Source"));
		action.ClickLink(locator.getProperty("Service_Ports"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Service_Ports"));
		//Click on different System Tools Links and verify title of page
		action.ClickLink(locator.getProperty("System_Tools"));	
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("System_Tools"));
		action.VerifyTitle(locator.getProperty("System_Tools"));
		action.ClickLink(locator.getProperty("SNMP_MIB"));	
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("SNMP_MIB_Download"));
		action.ClickLink(locator.getProperty("Maintenance_Tests"));	
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Maintenance_Tests"));
		action.VerifyTitle(locator.getProperty("Maintenance_Tests"));				
	   }
	catch(Exception e)
	   {
	   }
	
}
	
@Test(description="Verify the links of Communication Manager Tab call center links",groups={"Sanity"},priority=2)

public void Communication_Manager_Callcenter_Links() throws Exception{
	    action.RefreshPage();
		//Click on different Communication Manager Links and verify title of page
		action.ClickLink(locator.getProperty("Communication_Manager"));
		action.VerifyTitle(locator.getProperty("Feature_Management"));
		//Click on different Call Center Links and verify title of page
		
		action.ClickLink(locator.getProperty("Call_Center"));
		action.WaitForTitle(locator.getProperty("Call_Center"));
		action.VerifyTitle(locator.getProperty("Call_Center"));
		action.ClickLink(locator.getProperty("Agents"));
		action.WaitForTitle(locator.getProperty("Agents"));
		action.VerifyTitle(locator.getProperty("Agents"));
		
		action.ClickLink(locator.getProperty("Announcements"));
		action.VerifyStringValue(locator.getProperty("Announcements"));
		Thread.sleep(1000);
		
		//action.VerifyTitle(locator.getProperty("Announcements"));
		action.ClickLink(locator.getProperty("Audio_Group"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		action.WaitForTitle(locator.getProperty("Audio_Groups_list"));
		action.VerifyTitle(locator.getProperty("Audio_Groups_list"));
		action.ClickLink(locator.getProperty("Best_Service_Routing"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Best_Service_Routing_List"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Holiday_Table"));
		Thread.sleep(2000);
		action.VerifyTitle(locator.getProperty("Holiday_Table_List"));
		
		action.ClickLink(locator.getProperty("Media_Server"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Media_Server"));
		action.ClickLink(locator.getProperty("Variables"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Variables"));
		
		action.ClickLink(locator.getProperty("Vector"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		//action.VerifyTitle(locator.getProperty("Vector"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Vector_Directory_Number"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		action.VerifyTitle(locator.getProperty("Vector_Directory_Number"));
		action.ClickLink(locator.getProperty("Vector_Routing_Table"));
		Thread.sleep(2000);
		System.out.println (action.driver.getTitle());
		//action.VerifyTitle(locator.getProperty("Vector_Routing_Table_List"));
		Thread.sleep(2000);
		action.ClickLink(locator.getProperty("Service_Hours_Tables"));	
		Thread.sleep(2000);
		//action.VerifyTitle(locator.getProperty("Service_Hours_Table_List"));
		
	}

@Test(description="Verify the links of Communication Manager Tab",groups={"Sanity"},priority=3)
	public void Communication_Manager_Coverage() throws Exception{
	action.RefreshPage();
	//Click on different Communication Manager Links and verify title of page
		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);
		
		action.ClickLink(locator.getProperty("Coverage"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Coverage"));
		
		action.ClickLink(locator.getProperty("Coverage_Answer_Group"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Coverage_Answer_Group_List"));
		action.ClickLink(locator.getProperty("Coverage_Path"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Coverage_Path_List"));
		
		action.ClickLink(locator.getProperty("Coverage_Remote"));
		Thread.sleep(1000);
		
		action.VerifyTitle(locator.getProperty("Coverage_Remote_List"));
		
		action.ClickLink(locator.getProperty("Coverage_Time-of-day"));
		Thread.sleep(1000);

		action.VerifyStringValue(locator.getProperty("Coverage_Time-of-day"));
		action.ClickLink(locator.getProperty("Element_Cut-Through"));
		Thread.sleep(1000);
		
		action.VerifyTitle(locator.getProperty("Launch_Element_Cut_Through"));
	}

@Test(description="Verify the links of Communication Manager Tab End points links",groups={"Sanity"},priority=4)

public void Communication_Manager_Endpoints_Links() throws Exception{
		action.RefreshPage();
		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);

		//Click on different Endpoints Links and verify title of page
		action.ClickLink(locator.getProperty("Endpoints"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Endpoints"));
		
		action.ClickLink(locator.getProperty("Alias_Endpoint"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Alias_Endpoint_List"));
		action.ClickLink(locator.getProperty("Intra_Switch_CDR"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Intra_Switch_CDR_List"));
		action.ClickLink(locator.getProperty("Manage_Endpoints"));
		action.VerifyTitle(locator.getProperty("Endpoints_List"));
		
		action.ClickLink(locator.getProperty("Off_PBX_Telephone"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyStringValue(locator.getProperty("Off_PBX_Telephone"));
		
		action.ClickLink(locator.getProperty("Off_PBX_Configuration_Set"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Off_PBX_Configuration_Set"));
		action.ClickLink(locator.getProperty("Off_PBX_Endpoint_Mapping"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Off_PBX_Endpoint_Mapping"));
		
		action.ClickLink(locator.getProperty("Site_Data"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Site_Data"));
		action.ClickLink(locator.getProperty("Building"));	
		Thread.sleep(1000);


		action.VerifyTitle(locator.getProperty("Site_Data-Building_List"));
		action.ClickLink(locator.getProperty("Floor"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("Site_Data-Floor_List"));
		Thread.sleep(1000);

		action.ClickLink(locator.getProperty("Xmobile_Configuration"));
		Thread.sleep(2000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("Xmobile_Configuration_List"));
		Thread.sleep(1000);

}

	@Test(description="Verify the links of Communication Manager Tab Groups links",groups={"Sanity"},priority=5)

	public void Communication_Manager_Groups_Links() throws Exception{
		action.RefreshPage();
		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);

		//Click on different Groups Links and verify title of page
		action.ClickLink(locator.getProperty("Groups"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Groups"));
		action.ClickLink(locator.getProperty("Group_Page"));
		Thread.sleep(2000);

		action.VerifyTitle(locator.getProperty("Group_Page_List"));
		action.ClickLink(locator.getProperty("Hunt_Group"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Hunt_Group_List"));
		action.ClickLink(locator.getProperty("Intercom_Group"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Intercom_Group_List"));
		action.ClickLink(locator.getProperty("Pickup_Group"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Pickup_Group_List"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Terminating_Extension_Group"));
		Thread.sleep(2000);
		action.VerifyTitle(locator.getProperty("Terminating_Extension_Group_List"));
	}
	
	@Test(description="Verify the links of Communication Manager Tab Network links",groups={"Sanity"},priority=6)
	public void Communication_Manager_Network_Links() throws Exception{
		action.RefreshPage();

		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);
		//Click on different Network Links and verify title of page
		action.ClickLink(locator.getProperty("Network"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Network"));
		
		action.ClickLink(locator.getProperty("Automatic_Alternate_Routing_Analysis"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Automatic_Alternate_Routing_Analysis_List"));
		action.ClickLink(locator.getProperty("Automatic_Alternate_Routing_Digit_Conversion"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		action.VerifyTitle(locator.getProperty("AAR_Digit_Conversion_List"));
		action.ClickLink(locator.getProperty("Automatic_Route_Selection_Analysis"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Automatic_Route_Selection_Analysis_List"));
	
		action.ClickLink(locator.getProperty("Automatic_Route_Selection_Digit_Conversion"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("ARS_Digit_Conversion_List"));
		
		action.ClickLink(locator.getProperty("Automatic_Route_Selection_Toll"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("Automatic_Route_Selection_Toll_List"));
		Thread.sleep(1000);

		action.ClickLink(locator.getProperty("Data_Modules"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Data_Modules_List"));
		action.ClickLink(locator.getProperty("IP_Interfaces"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Interface_List"));
		action.ClickLink(locator.getProperty("IP_Network_Maps"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Network_Map"));
		action.ClickLink(locator.getProperty("IP_Network_Regions"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Network_Region_List"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Node_Names"));
		Thread.sleep(2000);

		action.VerifyTitle(locator.getProperty("Node_Names_List"));
		action.ClickLink(locator.getProperty("Route_Pattern"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Route_Pattern_List"));
		action.ClickLink(locator.getProperty("Signaling_Groups"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("Signaling_Group_List"));
		action.ClickLink(locator.getProperty("Trunk_Group"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Trunk_Group_List"));
	}
	@Test(description="Verify the links of Communication Manager Tab Parameters links",groups={"Sanity"},priority=7)
	public void Communication_Manager_Parameter_Links() throws Exception{
		action.RefreshPage();

		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);
		//Click on different Parameters Links and verify title of page
		action.ClickLink(locator.getProperty("Parameters"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Parameters"));

		action.ClickLink(locator.getProperty("System_Parameters-CDR_Options"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System_Parameters-CDR_Options_List"));
		action.ClickLink(locator.getProperty("System_Parameters-Customer_Options"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System_Parameters-Customer_Options_List"));
		action.ClickLink(locator.getProperty("System_Parameters-Features"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System_Parameters-Features_List"));
		action.ClickLink(locator.getProperty("System_Parameters-Security"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System_Parameters-Security_List"));
		action.ClickLink(locator.getProperty("System_Parameters-Special_Applications"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System_Parameters-Special_Applications_List"));
}

@Test(description="Verify the links of Communication Manager Tab System and Parameter Links",groups={"Sanity"},priority=8)
public void Communication_Manager_System() throws Exception{
		action.RefreshPage();
		action.ClickLink(locator.getProperty("Communication_Manager"));
		Thread.sleep(1000);
		
		//Click on different System Links and verify title of page
		action.ClickLink(locator.getProperty("System"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("System"));
		
		action.ClickLink(locator.getProperty("Abbreviated_Dialing_Enhanced"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Abbreviated_Dialing_Enhanced_List"));
		action.ClickLink(locator.getProperty("Abbreviated_Dialing-Group_or_System"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Abbreviated_Dialing_Group/System_List"));
		action.ClickLink(locator.getProperty("Abbreviated_Dialing_Personal"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Abbreviated-Dialing_Personal_List"));
		action.ClickLink(locator.getProperty("Authorization_Code"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Authorization_Code_List"));
		
		action.ClickLink(locator.getProperty("Class_Of_Restriction"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Class_Of_Restriction_(COR)_List"));
		
		action.ClickLink(locator.getProperty("Class_Of_Service"));	
		
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		
		//action.ClickLink(locator.getProperty("Class_of_Service_Group"));	
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		//action.VerifyTitle(locator.getProperty("Class_of_Service_Group_List"));
		action.ClickLink(locator.getProperty("Dialplan_Analysis"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Dialplan_Analysis_List"));
		action.ClickLink(locator.getProperty("Dialplan_Parameters"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Dialplan_Parameters_List"));
		action.ClickLink(locator.getProperty("Feature_Access_Codes"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Feature_Access_Codes_List"));
		
		action.ClickLink(locator.getProperty("Locations"));	
		Thread.sleep(2000);
		System.out.println (action.driver.getTitle());

		//action.VerifyTitle(locator.getProperty("Locations_List"));

		action.ClickLink(locator.getProperty("Tenantlink"));	
		Thread.sleep(2000);

		//action.VerifyTitle(locator.getProperty("Tenantlink"));
		action.ClickLink(locator.getProperty("Uniform_Dial_Plan"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Uniform_Dial_Plan_List"));
		
		action.ClickLink(locator.getProperty("Uniform_Dial_Plan_Groups"));	
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("UDP_Group_List"));
		System.out.println ("Reached");

		
		//Click on different Options Links and verify title of page
		action.ClickLink(locator.getProperty("Options"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Options"));
		action.ClickLink(locator.getProperty("Usage_Options"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Usage_Options"));
		action.ClickLink(locator.getProperty("NRP_Group"));	
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("NRP_Group"));
		
	}
	



@Test(description="Verify the links of communication server 1000 Tab",groups={"Sanity"},priority=9)
public void Communication_Server() throws Exception{
	try{
		//Click on different communication server Links and verify title of page

		action.RefreshPage();
		action.ClickLink(locator.getProperty("Communication_Server_1000"));
		Thread.sleep(1000);
		action.VerifyStringValue(locator.getProperty("Elements"));
	     }
	catch(Exception e){}
}

@Test(description="Verify the links of Conferencing Tab",groups={"Sanity"},priority=10)

public void Conferencing() throws Exception{
	try{
		action.RefreshPage();
		//Click on different Conferencing Links and verify title of page

		action.ClickLink(locator.getProperty("Conferencing"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Conferencing_Dashboard"));
		action.ClickLink(locator.getProperty("Dashboard"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Conferencin_Dashboard"));
		action.ClickLink(locator.getProperty("Sync"));	
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Domains_Synchronization"));
		
	}
	catch(Exception e){}
}

@Test(priority=11,description="Verify the links of ipoffice Tab",groups={"Sanity"})

public void IP_Office() throws Exception{
	try{
		//Click on different Ipoffice Links and verify title of page
		action.RefreshPage();

		action.ClickLink(locator.getProperty("IP_Office"));
		Thread.sleep(1000);
		
		action.VerifyStringValue(locator.getProperty("Elements"));
		action.ClickLink(locator.getProperty("Backup"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("IP_Office_Backup"));
		action.ClickLink(locator.getProperty("Restore"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Office_Restore"));
		
		action.ClickLink(locator.getProperty("Security_Configuration"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Office_Security_Configuration"));
		action.ClickLink(locator.getProperty("System_Configuration"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Office_System_Configuration"));
		
		action.ClickLink(locator.getProperty("Initiate_FailBack"));
		Thread.sleep(1000);
		action.VerifyStringValue(locator.getProperty("IP_Office_Backup_and_Restore"));
		action.ClickLink(locator.getProperty("File_Transfer"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		action.VerifyTitle(locator.getProperty("IP_Office_File_Transfer"));
		Thread.sleep(1000);
	
		//Click on different UCM/IP Office Application Server Links and verify title of page
		action.ClickLink(locator.getProperty("UCM_and_Application_Server"));
		Thread.sleep(1000);
		//action.ClickLink(locator.getProperty("Backup"));

		action.driver.findElement(By.xpath(locator.getProperty("UCM.Backup"))).click();
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("UCM_and_Application_Server_Backup"));
		action.driver.findElement(By.xpath(locator.getProperty("UCM.Restore"))).click();
		//Thread.sleep(1000);
		//action.ClickLink(locator.getProperty("Restore"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("UCM_Or_Application_Server_Restore"));
	    action.driver.findElement(By.xpath(locator.getProperty("UCM.Security_Configuration"))).click();
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Office_Security_Configuration"));
		action.driver.findElement(By.xpath(locator.getProperty("UCM.System_Configuration"))).click();
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("IP_Office_System_Configuration"));
		
	    action.driver.findElement(By.xpath(locator.getProperty("UCM.FileTransfer"))).click();
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("File_Transfer"));
		
		//Click on different VMpro Links and verify title of page
		action.ClickLink(locator.getProperty("VMPro"));
		Thread.sleep(1000);
		//action.VerifyStringValue(locator.getProperty("IPOFFICE-56454"));
		action.ClickLink(locator.getProperty("Call_Flow"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("VMPro_Call_Flow"));
	    action.driver.findElement(By.xpath(locator.getProperty("System_Configuration_Link"))).click();
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("VMPro_System_Configuration"));
		Thread.sleep(1000);
		
	}
	catch(Exception e){}
}


@Test(description="Verify the links of Meeting Tab ",groups={"Sanity"},priority=12)

public void Meeting() throws Exception{
	try{
		//Click on different Meeting Links and verify title of page

		action.RefreshPage();

		action.ClickLink(locator.getProperty("Meeting_Exchange"));
		Thread.sleep(1000);
		

		action.VerifyTitle(locator.getProperty("Meeting_Exchange"));
		
		//Click on different Client Registration and verify title of page
		action.ClickLink(locator.getProperty("Client_Registration"));
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());
		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("General"));	
		Thread.sleep(1000);
		System.out.println (action.driver.getTitle());

		//action.VerifyTitle("No_registered_servers");
		action.ClickLink(locator.getProperty("Resellers"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Time_zones"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Users"));	
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Wholesalers"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		
		//Click on different Audio Conferencing Links and verify title of page
		action.ClickLink(locator.getProperty("Audio_Conferencing"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Bridge_Features"));

		Thread.sleep(1000);
		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Conference_Features"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Call_Routing"));

		Thread.sleep(1000);
		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("System_Config"));	
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("General_Config"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Data_Conferencing"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		
		//Click on different Media Links and verify title of page
		action.ClickLink(locator.getProperty("Media"));	
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		//action.ClickLink(locator.getProperty("VMPro"));
		//Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Features"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Configuration"));	
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Web_Applications"));
		Thread.sleep(1000);

		//action.VerifyStringValue(locator.getProperty("No_registered_servers"));
		action.ClickLink(locator.getProperty("Services"));
		Thread.sleep(1000);

			
	}
	catch(Exception e){}
}

@Test(priority=13,description="Verify the links of Messaging Tab",groups={"Sanity"})

public void Messaging() throws Exception{
	try{
		
		//Click on different Messaging Links and verify title of page

				action.RefreshPage();

		action.ClickLink(locator.getProperty("Messaging"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Messaging"));
		action.ClickLink(locator.getProperty("System"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("System"));
		action.ClickLink(locator.getProperty("Class_Of_Service"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Class_Of_Service"));
		action.ClickLink(locator.getProperty("Storage_Destination"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Storage_Destination"));
		action.ClickLink(locator.getProperty("Subscriber"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Subscriber"));
		
		}
	catch(Exception e){}
}

@Test(priority=14,description="Verify the links of Presence Tab",groups={"Sanity"})

public void Presence() throws Exception{
	try{
		//Click on different Presence Links and verify title of page
		action.RefreshPage();
	
		action.ClickLink(locator.getProperty("Presence"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Presence_Configuration_Properties"));
		
		}
	catch(Exception e){}
}


@Test(priority=15,description="Verify the links of Routing Tab",groups={"Sanity"})

public void Routing() throws Exception{
	try{
		//Click on different Routing Links and verify title of page
		
		action.RefreshPage();
		action.ClickLink(locator.getProperty("Routing"));
		action.WaitForTitle(locator.getProperty("Introduction_to_Network_Routing_Policy"));
		action.VerifyTitle(locator.getProperty("Introduction_to_Network_Routing_Policy"));
		action.ClickLink(locator.getProperty("Domains"));
		action.WaitForTitle(locator.getProperty("Domain_Management"));
		action.VerifyStringValue(locator.getProperty("Domain_Management"));
		action.ClickLink(locator.getProperty("Locations"));
		action.WaitForTitle(locator.getProperty("Location"));
		action.VerifyTitle(locator.getProperty("Location"));
		action.ClickLink(locator.getProperty("Adaptations"));	
		action.VerifyTitle(locator.getProperty("Adaptations"));
		action.ClickLink(locator.getProperty("SIP_Entities"));
		action.VerifyTitle(locator.getProperty("SIP_Entities"));
		action.ClickLink(locator.getProperty("Entity_Links"));
		action.VerifyTitle(locator.getProperty("Entity_Links"));
		action.ClickLink(locator.getProperty("Time_Ranges"));
		action.VerifyTitle(locator.getProperty("Time_Ranges"));
		action.ClickLink(locator.getProperty("Routing_Policies"));
		action.VerifyTitle(locator.getProperty("Routing_Policies"));
		action.ClickLink(locator.getProperty("Dial_Patterns"));
		action.VerifyTitle(locator.getProperty("Dial_Patterns"));
		action.ClickLink(locator.getProperty("Regular_Expressions"));
		action.VerifyTitle(locator.getProperty("Regular_Expressions"));
		action.ClickLink(locator.getProperty("Defaults"));
		action.VerifyTitle(locator.getProperty("Personal_Settings"));
		
		
		}
	catch(Exception e){}
}

@Test(description="Verify the links of Session Manager Tab",groups={"Sanity"},priority=16)

public void SessionManager() throws Exception{
	try{
		//Click on different SessionManager Links and verify title of page
		action.RefreshPage();
		
		action.ClickLink(locator.getProperty("Session_Manager"));
		Thread.sleep(1000);
		
		action.WaitForTitle(locator.getProperty("Session_Manager"));
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Dashboard"));
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Session_Manager_Administration"));
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Communication_Profile_Editor"));
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//Click on different Network Configuration Links and verify title of page
		action.ClickLink(locator.getProperty("Network_Configuration"));
		Thread.sleep(3000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		action.ClickLink(locator.getProperty("Failover_Groups"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Local_Host_Name_Resolution"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Remote_Access"));

		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Firewall"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//Click on different Device and Configuration Links and verify title of page;
		action.ClickLink(locator.getProperty("Device_and_Location_Configuration"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Device_Settings_Groups"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Location_Settings"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//Click on different Application Configuration Links and verify title of page
		action.ClickLink(locator.getProperty("Application_Configuration"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Applications"));

		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Application_Sequences"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Conference_Factories"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Implicit_Users"));
		Thread.sleep(1000);

		action.VerifyStringValue(locator.getProperty("Implicit_Users"));
		action.ClickLink(locator.getProperty("NRS_Proxy_Users"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//Click on different System Status Links and verify title of page
		action.ClickLink(locator.getProperty("System_Status"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Entity_Monitoring"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Managed_Bandwidth_Usage"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Security_Module_Status"));	

		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Firewall_Status"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//action.ClickLink(locator.getProperty("Registration _Summary"));
		Thread.sleep(1000);

		//action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("User_Registrations"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Session_Counts"));
		Thread.sleep(1000);

		action.VerifyStringValue(locator.getProperty("Session_Counts"));
		action.ClickLink(locator.getProperty("User_Data_Storage"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		//Click on different System tools Links and verify title of page
		action.ClickLink(locator.getProperty("System_Tools"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Maintenance_Tests"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Tracer_Configuration"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Trace_Viewer"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Call_Routing_Test"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SNMP_MIB"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		action.ClickLink(locator.getProperty("Performance"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Call_Counts"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Performance"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("Location_Performance"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("System_Performance"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		action.ClickLink(locator.getProperty("SIP_Performance"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		action.ClickLink(locator.getProperty("Data_Collection"));
		Thread.sleep(1000);

		action.VerifyTitle(locator.getProperty("Session_Manager"));
		
		}
	catch(Exception e){}
}

@Test(description="Verify the links of Work Assignment Tab",groups={"Sanity"},priority=17)

public void Work_Assignment() throws Exception{
	try{
		//Click on different Work Assignment Links and verify title of page
		action.RefreshPage();
		
		action.ClickLink(locator.getProperty("Work_Assignment"));
		Thread.sleep(1000);
		System.out.println(action.driver.getTitle());
		action.VerifyTitle(locator.getProperty("Work_Assignment"));
		action.ClickLink(locator.getProperty("Assignment_Management"));
		Thread.sleep(1000);
		System.out.println(action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("Assignment_Management"));
		action.ClickLink(locator.getProperty("Attribute_Management"));
		Thread.sleep(1000);
		System.out.println(action.driver.getTitle());

		action.VerifyTitle(locator.getProperty("Attribute_Management"));
		//action.ClickLink(locator.getProperty("Property_Management"));
		//Thread.sleep(1000);
		//System.out.println(action.driver.getTitle());

		//action.VerifyTitle(locator.getProperty("Property_Management"));
		
		}
	catch(Exception e){}
}

@Test(description="Verify the links of Media Server Links",groups={"Sanity"},priority=18)

public void Media_Server() throws Exception{
	try{
		//Click on different Work Assignment Links and verify title of page
		action.RefreshPage();
		
		action.ClickLink(locator.getProperty("Media_Server"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Media_Server"));
		action.ClickLink(locator.getProperty("Cluster_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Cluster_Administration"));
		action.ClickLink(locator.getProperty("Server_Administration"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Server_Administration"));
		action.ClickLink(locator.getProperty("Application_Assignment"));
		Thread.sleep(1000);
		action.VerifyTitle(locator.getProperty("Application_Assignment"));
		
		}
	catch(Exception e){}
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
}

}
