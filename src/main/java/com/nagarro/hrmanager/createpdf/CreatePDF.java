package com.nagarro.hrmanager.createpdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nagarro.hrmanager.entity.Employee;

/**
 * 
 * @author subhodippaul
 *this class is used for craete PDF
 *
 */
public class CreatePDF {
	
	/**
	 * create the object of the listEmployee
	 */
	private List<Employee> listEmployee;
	
	/**
	 * method of create PDF
	 */
	public CreatePDF(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	/**
	 * method of the table header in the pdf
	 */
	private void tableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		/**
		 * set the header color
		 */
		cell.setBackgroundColor(Color.BLUE);
		
		/**
		 * set the padding of the header
		 */
		cell.setPadding(3);
		
		/**
		 * set type of the font
		 */
		 Font font = FontFactory.getFont(FontFactory.HELVETICA);
		 
		 	/**
		 	 * set font color
		 	 */
	        font.setColor(Color.WHITE);
	         
	        /**
	         * create the cell of Employee Code
	         */
	        cell.setPhrase(new Phrase("Employee Code", font));
	        table.addCell(cell);
	         
	        /**
	         * create the cell of Employee Name
	         */
	        cell.setPhrase(new Phrase("Employee Name", font));
	        table.addCell(cell);
	         
	        /**
	         * create the cell of Employee Location
	         */
	        cell.setPhrase(new Phrase("Employee Location", font));
	        table.addCell(cell);
	         
	        /**
	         * create the cell of Employee Email
	         */
	        cell.setPhrase(new Phrase("Employee Email", font));
	        table.addCell(cell);
	         
	        /**
	         * create the cell of Employee Date Of Birth
	         */
	        cell.setPhrase(new Phrase("Employee Date Of Birth", font));
	        table.addCell(cell); 
		
	}
	
	/**
	 * method to get the data in the pdf table
	 */
	private void tableData(PdfPTable table) {
		
		for(Employee employee:listEmployee) {
			
			/**
			 * to get the data of employee code 
			 */
			table.addCell(String.valueOf(employee.getEmployeeCode()));
			
			/**
			 * to get the data of employee name 
			 */
			table.addCell(employee.getEmployeeName());
			
			/**
			 * to get the data of employee location 
			 */
			table.addCell(employee.getEmployeeLocation());
			
			/**
			 * to get the data of employee email 
			 */
			table.addCell(employee.getEmployeeEmail());
			
			/**
			 * to get the employee date of birth 
			 */
			table.addCell(employee.getEmployeeDateOfBirth());
		}
		
	}
	
	/**
	 * this method is used to create the PDF page full design
	 */
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		
		/**
		 * set the document page A4 size
		 */
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		/**
		 * set the font
		 */
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		
		/**
		 * set font size
		 */
		font.setSize(15);
		
		/**
		 * set font color BLUE
		 */
		font.setColor(Color.BLUE);
		
		/**
		 * create the page heading employee list
		 */
		Paragraph paragraph = new Paragraph("Employee List",font);
		
		/**
		 * set the heading to the center
		 */
		paragraph.setAlignment(paragraph.ALIGN_CENTER);
		
		/**
		 * add to the document
		 */
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(5);
		
		/**
		 * pdf page width percentage
		 */
		table.setWidthPercentage(100f);
		
		/**
		 * set width
		 */
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
		table.setSpacingBefore(10);
		
		/**
		 * table header
		 */
		tableHeader(table);
		
		/**
		 * table data
		 */
		tableData(table);
		
		/**
		 * add table to the document 
		 */
		document.add(table);
		
		/**
		 * close the document
		 */
		document.close();
		
	}
	
	
	

}
