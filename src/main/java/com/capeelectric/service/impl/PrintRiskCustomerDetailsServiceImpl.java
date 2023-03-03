package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.model.CustomerDetails;
import com.capeelectric.repository.CustomerDetailsRepository;
import com.capeelectric.service.PrintRiskCustomerDetailsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintRiskCustomerDetailsServiceImpl implements PrintRiskCustomerDetailsService {

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	private static final Logger logger = LoggerFactory.getLogger(PrintRiskCustomerDetailsServiceImpl.class);
	@Override
	public String printRiskCustomerDetails(String userName, Integer riskId) throws RiskAssessmentException {

		if (userName != null && !userName.isEmpty() && riskId != null && riskId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("02 Customer Details.pdf"));

				Optional<CustomerDetails> customerDetailsRepo = customerDetailsRepository.findByRiskId(riskId);
				CustomerDetails customerDetails = customerDetailsRepo.get();
//				ClientDetails clientDetailsRepo1 = clientDetailsRepo.get();

				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				document.open();

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
//				headertable.setSpacingBefore(f); // Space before table

				PdfPCell label = new PdfPCell(new Paragraph("RISK ASSESSMENT REPORT", font11B));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
				label.setBorder(PdfPCell.NO_BORDER);
				label.setFixedHeight(20f);
				headertable.addCell(label);
				document.add(headertable);

				float[] pointColumnWidths = { 15F, 60F, 105F };
				PdfPTable table100 = new PdfPTable(pointColumnWidths);

				table100.setWidthPercentage(100); // Width 100%
				table100.setSpacingBefore(10f); // Space before table
				table100.setSpacingAfter(5f); // Space after table
				table100.getDefaultCell().setBorder(15);

				PdfPCell cell4 = new PdfPCell(new Paragraph("\r\n\r\n01", font10B));
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(new Paragraph("\r\n\r\n  Organisation Name", font10B));
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setFixedHeight(60f);
				table100.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getOrganisationName(), font10N));
				cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph("\r\n\r\n02", font10B));
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph("\r\n\r\n  Address", font10B));
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setFixedHeight(60f);
				table100.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getAddress(), font10N));
				cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph("\r\n\r\n03", font10B));
				cell10.setHorizontalAlignment(Element.ALIGN_CENTER);

				table100.addCell(cell10);

				PdfPCell cell11 = new PdfPCell(new Paragraph("\r\n\r\n  Project Name", font10B));
				cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell11.setFixedHeight(60f);
				table100.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getProjectName(), font10N));
				cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell12);

				PdfPCell cell13 = new PdfPCell(new Paragraph("\r\n\r\n04", font10B));
				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(new Paragraph("\r\n\r\n  Project Description", font10B));
				cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell14.setFixedHeight(60f);
				table100.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getProjectDescription(), font10N));
				cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph("\r\n\r\n05", font10B));
				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell16);

				PdfPCell cell17 = new PdfPCell(new Paragraph("\r\n\r\n  Contact Person Name", font10B));
				cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell17.setFixedHeight(60f);
				table100.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getContactPersonName(), font10N));
				cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell18);

				PdfPCell cell19 = new PdfPCell(new Paragraph("\r\n\r\n06", font10B));
				cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell19);

				PdfPCell cell20 = new PdfPCell(new Paragraph("\r\n\r\n  Contact Number", font10B));
				cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell20.setFixedHeight(60f);
				table100.addCell(cell20);

				PdfPCell cell21 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getContactNumber(), font10N));
				cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell21);

				PdfPCell cell22 = new PdfPCell(new Paragraph("\r\n\r\n07", font10B));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell22);

				PdfPCell cell23 = new PdfPCell(new Paragraph("\r\n\r\n  Prepared By", font10B));
				cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell23.setFixedHeight(60f);
				table100.addCell(cell23);

				PdfPCell cell24 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getPreparedBy(), font10N));
				cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell24);

				PdfPCell cell25 = new PdfPCell(new Paragraph("\r\n\r\n08", font10B));
				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
				table100.addCell(cell25);

				PdfPCell cell26 = new PdfPCell(new Paragraph("\r\n\r\n  Verified By", font10B));
				cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell26.setFixedHeight(60f);
				table100.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph("\r\n\r\n" + customerDetails.getVerifiedBy(), font10N));
				cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
				table100.addCell(cell27);
				document.add(table100);

				document.close();
				writer.close();
				
				return customerDetails.getProjectName();

			} catch (Exception e) {
				logger.error("Exception being thrown from PDF generation" +e.getMessage());
				throw new RiskAssessmentException("PDF Generation Failed");
			}

		} else

		{
			throw new RiskAssessmentException("Invalid Inputs");
		}

	}
}
