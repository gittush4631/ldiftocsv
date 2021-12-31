package ldiftocsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.ldif.LdifReader;

public class ConvertLDIFtoCSV {
	public static void main(String[] args) throws LdapLdifException, IOException {
		File f = new File("/Users/tusharpatil/test/user_manager_export.ldif");
		FileReader fread = new FileReader(f);
		LdifReader lr = new LdifReader();
		String cn,uid,manager,managerid = null,firstdn,managername="";
		int i=0;
		List<LdifEntry> entries = lr.parseLdifFile("/Users/tusharpatil/test/user_manager_export.ldif");
	;
		File f1=new File("/Users/tusharpatil/test/managerreport.csv");
		FileWriter fwrite=new FileWriter(f1);
		 fwrite.write("UID, Employee Name, Manager, Manager UID");
		   fwrite.write("\n");
		//iterate the entries
		for (LdifEntry entry : entries) {
		    firstdn = entry.getDn().getName();
		    //System.out.println("DN name"+name);
		   //if (firstdn.equals(dn)) {
		    if((entry.get("cn") !=null)  && (entry.get("uid")!=null) && (entry.get("manager")!=null)) {
		        cn=entry.get("cn").toString();
		        cn=cn.substring(4);
		        uid=entry.get("uid").toString();
		        uid=uid.substring(5);
		        
		        
		        //code to get manager from its dn
		        manager=entry.get("manager").toString();
		        manager=manager.substring(9);
		       
		        for (LdifEntry entry1 : entries) {
		        	String managerdn=entry1.getDn().getName();
		        	managerdn= managerdn.replaceAll("\\s","");
		          if(manager.equalsIgnoreCase(managerdn)) {
		        	  managername=entry1.get("cn").toString();
		        	  managername=managername.substring(4);
		        	  managerid=entry1.get("uid").toString();
		        	  managerid=managerid.substring(5);
		          }
		        }
		        
		        
		    	/*System.out.println(uid);
		    	System.out.println(cn);
		    	System.out.println(managername);
		    	System.out.println(managerid);*/
		        
		        if(uid!=null && cn!=null && managername!=null && managerid!=null) {
		        fwrite.write(uid);
				   fwrite.write(",");
				   fwrite.write(cn);
				   fwrite.write(",");
				   fwrite.write(managername);
				   fwrite.write(",");
				   fwrite.write(managerid);
				   fwrite.write("\n");
				   System.out.println("processing entry :" +i);
				    i++;
		        }
		    	//System.out.println(cn);
		    }
		        
		   // }
		}
		System.out.println("End");
		}
		
		
	}


