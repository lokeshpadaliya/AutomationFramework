package utility;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {

	public static StringBuilder sb;
	public void htmlWriter()
	{	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sb=new StringBuilder();
		sb.append("<html><head><title>Test Report </title></head>");
		sb.append("<body bgcolor=\"#E6E6FA\">");
		sb.append("<h1 align=center>Test Execution Report</h1><BR>");
		sb.append("<p><a href=\"#summary\">Jump to Execution Summary</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reporting Time:&nbsp;"+timeStamp+"</p>");
		sb.append("<h3 align=center>Detailed Report</h3>");
		sb.append("<table border=\"1\" bordercolor=\"#000000\" align=\"center\" width=\"50%\">");
		sb.append("<tr style=background-color:#BDB76B;color:#ffffff;><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td></tr>");
	}
	public void htmlResultWriter(int id, String testName, String result) throws IOException
	{	
		sb.append("<tr id="+(id+1)+"><td><b>"+id+"</b></td><td><b>"+testName+"</b></td><td><b>"+result+"</b></td></tr>");     
    }
	public void htmlSummary(int pass, int fail, int notexecuted) throws IOException
    {
		sb.append("</table>");
		sb.append("<h3 id=\"summary\" align=center>Execution Summary</h3>");
		sb.append("<table border=\"1\" bordercolor=\"#000000\" align=\"center\" width=\"50%\">");
		sb.append("<tr style=background-color:#BDB76B;color:#ffffff;><td><b>Total Pass</b></td><td><b>Total Fail</b></td><td><b>Total Not Executed</b></td></tr>");
		sb.append("<tr id=><td><b>"+pass+"</b></td><td><b>"+fail+"</b></td><td><b>"+notexecuted+"</b></td></tr>");
    	sb.append("</table>");
    	sb.append("</body></html>");
    }

    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator+fileName;
        File file = new File(tempFile);

        if (file.exists()) {
            try {
                File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();
        EmailReport.sendEmail();
    }
}