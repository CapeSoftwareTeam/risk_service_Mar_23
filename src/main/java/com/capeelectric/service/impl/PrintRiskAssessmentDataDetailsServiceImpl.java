package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.model.CalculatedRisk;
import com.capeelectric.model.Losses;
import com.capeelectric.model.Protection;
import com.capeelectric.model.RiskProtection;
import com.capeelectric.model.StructureAttributes;
import com.capeelectric.model.StructureCharacteristics;
import com.capeelectric.repository.RiskAssessmentRepository;
import com.capeelectric.service.PrintRiskAssessmentDataDetailsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintRiskAssessmentDataDetailsServiceImpl implements PrintRiskAssessmentDataDetailsService {

	@Autowired
	private RiskAssessmentRepository riskAssessmentRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PrintRiskAssessmentDataDetailsServiceImpl.class);

	@Override
	public void printRiskAssessmentDataDetails(String userName, Integer riskId) throws RiskAssessmentException {

		if (userName != null && !userName.isEmpty() && riskId != null && riskId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			 String classLevel="";
			try {

				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream("04 RiskAssessment Details.pdf"));

				Optional<StructureCharacteristics> structureCharacteristicsRepo = riskAssessmentRepository
						.findByRiskId(riskId);
				StructureCharacteristics structureCharacteristicsDetails = structureCharacteristicsRepo.get();

				List<StructureAttributes> structureAtt = structureCharacteristicsDetails.getStructureAttributes();
				StructureAttributes structureAttributes = structureAtt.get(0);

				List<Protection> protect = structureCharacteristicsDetails.getProtection();

				List<Losses> loss = structureCharacteristicsDetails.getLosses();

				List<RiskProtection> riskprotect = structureCharacteristicsDetails.getRiskProtection();

				List<CalculatedRisk> calculated = structureCharacteristicsDetails.getCalculatedRisk();

				document.open();

				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				
				Font protectedfont11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.GREEN);
				
				Font unProtectedfont11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.RED);

				Font font11N = new Font(BaseFont.createFont(), 11, Font.NORMAL, BaseColor.BLACK);
				
				Font protectedValues10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.GREEN);
				
				Font protectedValues10B = new Font(BaseFont.createFont(), 10, Font.BOLD, BaseColor.BLACK);
				
				Font protectedValues11N = new Font(BaseFont.createFont(), 11, Font.NORMAL, BaseColor.GREEN);
				
				Font unProtectedValues11N = new Font(BaseFont.createFont(), 11, Font.NORMAL, BaseColor.RED);
				
				Font unProtectedValues10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.RED);

				float[] pointColumnHeadLabel = { 100F };

				PdfPTable tableHead = new PdfPTable(1);
				tableHead.setWidthPercentage(100); // Width 100%
				tableHead.setSpacingBefore(5f); // Space before table
				tableHead.setSpacingAfter(5f); // Space after table

				PdfPCell RiskDetailsCell = new PdfPCell(
						new Paragraph("4. RISK ASSESSMENT SELECTION & PARAMETERS", font11B));
				RiskDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				RiskDetailsCell.setBackgroundColor(new GrayColor(0.90f));
				RiskDetailsCell.setBorder(PdfPCell.NO_BORDER);
				RiskDetailsCell.setFixedHeight(25f);
				tableHead.addCell(RiskDetailsCell);
				document.add(tableHead);

				PdfPTable table1 = new PdfPTable(pointColumnHeadLabel);
				table1.setWidthPercentage(100); // Width 100%
				table1.setSpacingBefore(5f); // Space before table
				// table1.setSpacingAfter(5f); // Space after table

				PdfPCell dataCell = new PdfPCell(new Paragraph("STRUCTURE'S CHARACTERISTICS", font10B));
				dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				dataCell.setBackgroundColor(new GrayColor(0.90f));
				dataCell.setFixedHeight(25f);
				table1.addCell(dataCell);
				document.add(table1);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				// table.setSpacingBefore(5f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("Location :", font11N));
				// cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				cell.setFixedHeight(25f);
				table.addCell(cell);
				PdfPCell cell1 = new PdfPCell(new Paragraph(structureCharacteristicsDetails.getLocation(), font11N));
				cell1.setGrayFill(0.92f);
				// cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Ground flash density:", font11N));
				cell2.setFixedHeight(25f);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getGroundFlashDensity(), font11N));
				table.addCell(cell3);

				PdfPCell cell126 = new PdfPCell(new Paragraph("Type of Building:", font11N));
				cell126.setGrayFill(0.92f);
				cell126.setFixedHeight(25f);
				table.addCell(cell126);
				PdfPCell cell127 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getTypeOfBuilding(), font11N));
				cell127.setGrayFill(0.92f);
				table.addCell(cell127);

				PdfPCell cell120 = new PdfPCell(new Paragraph("Structure screening effectiveness:", font11N));
				cell120.setFixedHeight(25f);
				table.addCell(cell120);
				PdfPCell cell121 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getStructureScreeningEffectiveness(), font11N));
				// cell121.setGrayFill(0.92f);
				// cell121.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell121);

				PdfPCell cell122 = new PdfPCell(new Paragraph("Length: (In meters)", font11N));
				cell122.setGrayFill(0.92f);
				cell122.setFixedHeight(25f);
				table.addCell(cell122);
				PdfPCell cell123 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtrusionLenght(), font11N));
				cell123.setGrayFill(0.92f);
				table.addCell(cell123);

				PdfPCell cell13 = new PdfPCell(new Paragraph("Width: (In meters)", font11N));
				cell13.setFixedHeight(25f);
				table.addCell(cell13);
				PdfPCell cell14 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtrusionWidth(), font11N));
				// cell14.setGrayFill(0.92f);
				// cell14.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(new Paragraph("Height: (In meters)", font11N));
				// cell15.setBorder(PdfPCell.NO_BORDER);
				cell15.setGrayFill(0.92f);
				cell15.setFixedHeight(25f);
				table.addCell(cell15);
				PdfPCell cell16 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtrusionHeight(), font11N));
				cell16.setGrayFill(0.92f);
				// cell16.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell16);

				PdfPCell cell17 = new PdfPCell(new Paragraph("Height of highest roof protrusion:  (In meters)", font11N));
				// cell17.setBorder(PdfPCell.NO_BORDER);
				// cell17.setGrayFill(0.92f);
				cell17.setFixedHeight(25f);
				table.addCell(cell17);
				PdfPCell cell18 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getHeighestRoofProtrusion(), font11N));
				// cell18.setGrayFill(0.92f);
				// cell18.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell18);

				PdfPCell cell20 = new PdfPCell(new Paragraph("Collection area of structure:", font11N));
				// cell20.setBorder(PdfPCell.NO_BORDER);
				cell20.setGrayFill(0.92f);
				cell20.setFixedHeight(25f);
				table.addCell(cell20);
				PdfPCell cell231 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getCollectionAreaOfStructure(), font11N));
				cell231.setGrayFill(0.92f);
				// cell231.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell231);

				PdfPCell cell21 = new PdfPCell(new Paragraph("Collection area of structure with protrusion:", font11N));
				// cell21.setBorder(PdfPCell.NO_BORDER);
				// cell21.setGrayFill(0.92f);
				cell21.setFixedHeight(25f);
				table.addCell(cell21);
				PdfPCell cell29 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getCollAreaOfStrucWithProtrusion(), font11N));
				// cell29.setGrayFill(0.92f);
				// cell29.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell29);

				PdfPCell cell212 = new PdfPCell(new Paragraph("Collection area near the structure:", font11N));
				// cell212.setBorder(PdfPCell.NO_BORDER);
				cell212.setGrayFill(0.92f);
				cell212.setFixedHeight(25f);
				table.addCell(cell212);
				PdfPCell cell291 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getCollAreaOfNearStructure(), font11N));
				cell291.setGrayFill(0.92f);
				// cell291.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell291);

				PdfPCell cell30 = new PdfPCell(new Paragraph("Height of nearby structure:", font11N));
				// cell30.setBorder(PdfPCell.NO_BORDER);
				// cell30.setGrayFill(0.92f);
				cell30.setFixedHeight(25f);
				table.addCell(cell30);
				PdfPCell cell31 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getHeightNearByStructure(), font11N));
				// cell31.setGrayFill(0.92f);
				// cell31.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Electrical / telephone service line:", font11N));
				// cell32.setBorder(PdfPCell.NO_BORDER);
				cell32.setFixedHeight(25f);
				cell32.setGrayFill(0.92f);
				table.addCell(cell32);
				PdfPCell cell33 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getTelephoneServiceLine(), font11N));
				cell33.setGrayFill(0.92f);
				// cell33.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell33);

				PdfPCell cell34 = new PdfPCell(new Paragraph("Environment:", font11N));
				// cell34.setBorder(PdfPCell.NO_BORDER);
				cell34.setFixedHeight(25f);
				// cell34.setGrayFill(0.92f);
				table.addCell(cell34);
				PdfPCell cell35 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getEnvironment(), font11N));
				// cell35.setGrayFill(0.92f);
				// cell35.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph("No. of dangerous event on structure:", font11N));
				// cell36.setBorder(PdfPCell.NO_BORDER);
				cell36.setGrayFill(0.92f);
				cell36.setFixedHeight(25f);
				table.addCell(cell36);
				PdfPCell cell37 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getNoOfDangerousEventOnStructure(), font11N));
				cell37.setGrayFill(0.92f);
				// cell37.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell37);

				PdfPCell cell38 = new PdfPCell(new Paragraph("No. of dangerous event near the structure:", font11N));
				// cell38.setBorder(PdfPCell.NO_BORDER);
				// cell38.setGrayFill(0.92f);
				cell38.setFixedHeight(25f);
				table.addCell(cell38);
				PdfPCell cell39 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getNoOfDangerousEventNearStructure(), font11N));
				// cell39.setGrayFill(0.92f);
				// cell39.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(new Paragraph("Protection required for part of building:", font11N));
				// cell40.setBorder(PdfPCell.NO_BORDER);
				cell40.setGrayFill(0.92f);
				cell40.setFixedHeight(25f);
				table.addCell(cell40);
				PdfPCell cell41 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtectionPartOFBuilding(), font11N));
				cell41.setGrayFill(0.92f);
				// cell41.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Paragraph("Length: (In meters)", font11N));
				// cell42.setBorder(PdfPCell.NO_BORDER);
				// cell42.setGrayFill(0.92f);
				cell42.setFixedHeight(25f);
				table.addCell(cell42);
				PdfPCell cell43 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtectionLenght(), font11N));
				// cell43.setGrayFill(0.92f);
				// cell43.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph("Width: (In meters)", font11N));
				// cell44.setBorder(PdfPCell.NO_BORDER);
				cell44.setGrayFill(0.92f);
				cell44.setFixedHeight(25f);
				table.addCell(cell44);
				PdfPCell cell145 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtectionWidth(), font11N));
				cell145.setGrayFill(0.92f);
				// cell145.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell145);

				PdfPCell cell46 = new PdfPCell(new Paragraph("Height: (In meters)", font11N));
				// cell46.setBorder(PdfPCell.NO_BORDER);
				// cell46.setGrayFill(0.92f);
				cell46.setFixedHeight(25f);
				table.addCell(cell46);
				PdfPCell cell47 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtectionHeight(), font11N));
				// cell47.setGrayFill(0.92f);
				// cell47.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell47);

				PdfPCell cell48 = new PdfPCell(new Paragraph("Collection area:", font11N));
				// cell48.setBorder(PdfPCell.NO_BORDER);
				cell48.setGrayFill(0.92f);
				cell48.setFixedHeight(25f);
				table.addCell(cell48);
				PdfPCell cell49 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getProtectionCollectionArea(), font11N));
				cell49.setGrayFill(0.92f);
				// cell49.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell49);

				PdfPCell cell50 = new PdfPCell(new Paragraph("Adjacent Building:", font11N));
				// cell50.setBorder(PdfPCell.NO_BORDER);
				// cell50.setGrayFill(0.92f);
				cell50.setFixedHeight(25f);
				table.addCell(cell50);
				PdfPCell cell51 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getAdjacentBuilding(), font11N));
				// cell51.setGrayFill(0.92f);
				// cell51.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell51);

				PdfPCell cell452 = new PdfPCell(new Paragraph("Length: (In meters)", font11N));
				// cell452.setBorder(PdfPCell.NO_BORDER);
				cell452.setGrayFill(0.92f);
				cell452.setFixedHeight(25f);
				table.addCell(cell452);
				PdfPCell cell53 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getAdjacentLength(), font11N));
				cell53.setGrayFill(0.92f);
				// cell53.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell53);

				PdfPCell cell54 = new PdfPCell(new Paragraph("Width: (In meters)", font11N));
				// cell54.setBorder(PdfPCell.NO_BORDER);
				// cell54.setGrayFill(0.92f);
				cell54.setFixedHeight(25f);
				table.addCell(cell54);
				PdfPCell cell155 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getAdjacentWidth(), font11N));
				// cell155.setGrayFill(0.92f);
				// cell155.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell155);

				PdfPCell cell56 = new PdfPCell(new Paragraph("Height: (In meters)", font11N));
				// cell56.setBorder(PdfPCell.NO_BORDER);
				cell56.setFixedHeight(25f);
				cell56.setGrayFill(0.92f);
				table.addCell(cell56);
				PdfPCell cell57 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getAdjacentHeight(), font11N));
				cell57.setGrayFill(0.92f);
				// cell57.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell57);

				PdfPCell cell58 = new PdfPCell(new Paragraph("Collection area of adjacent structure:", font11N));
				// cell58.setBorder(PdfPCell.NO_BORDER);
				// cell58.setGrayFill(0.92f);
				cell58.setFixedHeight(25f);
				table.addCell(cell58);
				PdfPCell cell59 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getCollAreaOfAdjacentStruc(), font11N));
				// cell59.setGrayFill(0.92f);
				// cell59.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell59);

				PdfPCell cell60 = new PdfPCell(
						new Paragraph("No. of dangerous event on the adjacent \r\n" + "structure:", font11N));
				// cell60.setBorder(PdfPCell.NO_BORDER);
				cell60.setGrayFill(0.92f);
				cell60.setFixedHeight(25f);
				table.addCell(cell60);
				PdfPCell cell61 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getNoOfDangEventOnAdjacentStruc(), font11N));
				cell61.setGrayFill(0.92f);
				// cell61.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell61);

				PdfPCell cell62 = new PdfPCell(new Paragraph("No. of people in the building:", font11N));
				// cell62.setBorder(PdfPCell.NO_BORDER);
				// cell62.setGrayFill(0.92f);
				cell62.setFixedHeight(25f);
				table.addCell(cell62);
				PdfPCell cell63 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getNoOfPeopleInBuilding(), font11N));
				// cell63.setGrayFill(0.92f);
				// cell63.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell63);

				PdfPCell cell64 = new PdfPCell(new Paragraph("No. of people in the zone:", font11N));
				// cell64.setBorder(PdfPCell.NO_BORDER);
				cell64.setGrayFill(0.92f);
				cell64.setFixedHeight(25f);
				table.addCell(cell64);
				PdfPCell cell65 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getNoOfPeopleInZone(), font11N));
				cell65.setGrayFill(0.92f);
				// cell65.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell65);

				PdfPCell cell66 = new PdfPCell(
						new Paragraph("Number of hours/day people are present in \r\n" + "the building:", font11N));
				// cell66.setBorder(PdfPCell.NO_BORDER);
				// cell66.setGrayFill(0.92f);
				cell66.setFixedHeight(25f);
				table.addCell(cell66);
				PdfPCell cell67 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getDayPeoplePresentBuilding(), font11N));
				// cell67.setGrayFill(0.92f);
				// cell67.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell67);

				PdfPCell cell68 = new PdfPCell(
						new Paragraph("Number of hours/Year people are present in \r\n" + "the building:", font11N));
				// cell68.setBorder(PdfPCell.NO_BORDER);
				cell68.setGrayFill(0.92f);
				cell68.setFixedHeight(25f);
				table.addCell(cell68);
				PdfPCell cell69 = new PdfPCell(
						new Paragraph(structureCharacteristicsDetails.getYearPeoplePresentBuilding(), font11N));
				cell69.setGrayFill(0.92f);
				// cell69.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell69);
				document.add(table);

				PdfPTable table11 = new PdfPTable(pointColumnHeadLabel);
				table11.setWidthPercentage(100); // Width 100%
				table11.setSpacingBefore(15f); // Space before table
				// table11.setSpacingAfter(5f); // Space after table

				PdfPCell dataCell1 = new PdfPCell(new Paragraph("STRUCTURE'S ATTRIBUTES", font10B));
				dataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dataCell1.setFixedHeight(25f);
				table11.addCell(dataCell1);
				document.add(table11);

				PdfPTable table22 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table22.setWidthPercentage(100); // Width 100%
				// table22.setSpacingBefore(5f); // Space before table
//				table22.setSpacingAfter(10f); // Space after table
				table22.getDefaultCell().setBorder(0);

				PdfPCell cell70 = new PdfPCell(new Paragraph("Type of floor surface :", font11N));
				// cell70.setBorder(PdfPCell.NO_BORDER);
				cell70.setGrayFill(0.92f);
				cell70.setFixedHeight(25f);
				table22.addCell(cell70);
				PdfPCell cell72 = new PdfPCell(new Paragraph(structureAttributes.getStTypeOfFloorSurface(), font11N));
				cell72.setGrayFill(0.92f);
				table22.addCell(cell72);

				PdfPCell cell73 = new PdfPCell(new Paragraph("Additional protection:", font11N));
				cell73.setFixedHeight(25f);
				table22.addCell(cell73);
				PdfPCell cell74 = new PdfPCell(new Paragraph(structureAttributes.getStAdditionalProtection(), font11N));
				// cell74.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell74);

				PdfPCell cell75 = new PdfPCell(new Paragraph("Risk of fire:", font11N));
				cell75.setGrayFill(0.92f);
				cell75.setFixedHeight(25f);
				// cell75.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell75);
				PdfPCell cell76 = new PdfPCell(new Paragraph(structureAttributes.getStRiskOfFire(), font11N));
				cell76.setGrayFill(0.92f);
				// cell76.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell76);

				PdfPCell cell78 = new PdfPCell(new Paragraph("Fire protection measures:", font11N));
				// cell78.setBorder(PdfPCell.NO_BORDER);
				cell78.setFixedHeight(25f);
				table22.addCell(cell78);
				PdfPCell cell79 = new PdfPCell(
						new Paragraph(structureAttributes.getStFireProtectionMeasure(), font11N));
				// cell79.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell79);

				PdfPCell cell80 = new PdfPCell(new Paragraph("Type of internal wiring:", font11N));
				cell80.setGrayFill(0.92f);
				cell80.setFixedHeight(25f);
				// cell80.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell80);
				PdfPCell cell81 = new PdfPCell(new Paragraph(structureAttributes.getStTypeOfInternalWiring(), font11N));
				cell81.setGrayFill(0.92f);
				// cell81.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell81);
				document.add(table22);

//				document.newPage();

				PdfPTable table3 = new PdfPTable(pointColumnHeadLabel);
				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(15f); // Space before table
//				SystemRoomShieldTable.setSpacingAfter(5f); // Space after table

				PdfPCell shieldCell = new PdfPCell(new Paragraph("INCOMING LINES", font10B));
				shieldCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				shieldCell.setBackgroundColor(new GrayColor(0.93f));
				shieldCell.setFixedHeight(25f);
				table3.addCell(shieldCell);
				document.add(table3);

				PdfPTable table4 = new PdfPTable(pointColumnWidths1);
				table4.setWidthPercentage(100); // Width 100%
				// table4.setSpacingBefore(10f); // Space before table
				table4.getDefaultCell().setBorder(0);

				PdfPCell cell112 = new PdfPCell(new Paragraph("Total No. of lines:", font11N));
				// cell112.setBorder(PdfPCell.NO_BORDER);
				cell112.setFixedHeight(25f);
				cell112.setGrayFill(0.92f);
				table4.addCell(cell112);
				PdfPCell cell113 = new PdfPCell(new Paragraph(structureAttributes.getTotalNoOfLines(), font11N));
				cell113.setGrayFill(0.92f);
				// cell113.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell113);

				PdfPCell cell114 = new PdfPCell(new Paragraph("Number of power lines:", font11N));
				// cell114.setBorder(PdfPCell.NO_BORDER);
				cell114.setFixedHeight(25f);
				// cell114.setGrayFill(0.92f);
				table4.addCell(cell114);
				PdfPCell cell115 = new PdfPCell(new Paragraph(structureAttributes.getNoOfPowerLines(), font11N));
				// cell115.setGrayFill(0.92f);
				// cell115.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell115);

				PdfPCell cell110 = new PdfPCell(new Paragraph("Type of Power lines:", font11N));
				// cell110.setBorder(PdfPCell.NO_BORDER);
				cell110.setGrayFill(0.92f);
				cell110.setFixedHeight(25f);
				table4.addCell(cell110);
				PdfPCell cell111 = new PdfPCell(new Paragraph(structureAttributes.getTypeOfPowerLines(), font11N));
				cell111.setGrayFill(0.92f);
				// cell111.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell111);

				PdfPCell cell116 = new PdfPCell(new Paragraph("Length of Power line: (In meters)", font11N));
				// cell116.setBorder(PdfPCell.NO_BORDER);
				cell116.setFixedHeight(25f);
				// cell116.setGrayFill(0.92f);
				table4.addCell(cell116);
				PdfPCell cell117 = new PdfPCell(new Paragraph(structureAttributes.getLengthOfPowerLines(), font11N));
				// cell117.setGrayFill(0.92f);
				// cell117.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell117);

				PdfPCell cell118 = new PdfPCell(new Paragraph("Shielding, grounding, isolation:", font11N));
				// cell118.setBorder(PdfPCell.NO_BORDER);
				cell118.setFixedHeight(25f);
				cell118.setGrayFill(0.92f);
				table4.addCell(cell118);
				PdfPCell cell119 = new PdfPCell(
						new Paragraph(structureAttributes.getShieldingGroundingIsolation(), font11N));
				cell119.setGrayFill(0.92f);
				// cell119.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell119);

				PdfPCell cell200 = new PdfPCell(new Paragraph("Collection area of the Power lines:", font11N));
				// cell200.setBorder(PdfPCell.NO_BORDER);
				// cell200.setGrayFill(0.92f);
				cell200.setFixedHeight(25f);
				table4.addCell(cell200);
				PdfPCell cell201 = new PdfPCell(new Paragraph(structureAttributes.getCollAreaOfPowerLines(), font11N));
				// cell201.setGrayFill(0.92f);
				// cell201.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell201);

				PdfPCell cell202 = new PdfPCell(new Paragraph("Collection area near the lines:", font11N));
				// cell202.setBorder(PdfPCell.NO_BORDER);
				cell202.setFixedHeight(25f);
				cell202.setGrayFill(0.92f);
				table4.addCell(cell202);
				PdfPCell cell203 = new PdfPCell(new Paragraph(structureAttributes.getCollAreaOfNearLines(), font11N));
				cell203.setGrayFill(0.92f);
				// cell203.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell203);

				PdfPCell cell204 = new PdfPCell(new Paragraph("No. of dangerous event near the Power lines:", font11N));
				// cell204.setBorder(PdfPCell.NO_BORDER);
				// cell204.setGrayFill(0.92f);
				cell204.setFixedHeight(25f);
				table4.addCell(cell204);
				PdfPCell cell205 = new PdfPCell(
						new Paragraph(structureAttributes.getEventNearThePowerLines(), font11N));
				// cell205.setGrayFill(0.92f);
				// cell205.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell205);

				PdfPCell cell206 = new PdfPCell(new Paragraph("No. of dangerous event on the Power lines:", font11N));
				// cell206.setBorder(PdfPCell.NO_BORDER);
				cell206.setGrayFill(0.92f);
				cell206.setFixedHeight(25f);
				table4.addCell(cell206);
				PdfPCell cell207 = new PdfPCell(new Paragraph(structureAttributes.getEventOnThePowerLines(), font11N));
				cell207.setGrayFill(0.92f);
				// cell207.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell207);

				PdfPCell cell209 = new PdfPCell(new Paragraph("Number of telecommunication lines:", font11N));
				// cell209.setBorder(PdfPCell.NO_BORDER);
				// cell209.setGrayFill(0.92f);
				cell209.setFixedHeight(25f);
				table4.addCell(cell209);
				PdfPCell cell300 = new PdfPCell(new Paragraph(structureAttributes.getNoOfTelecomLines(), font11N));
				// cell300.setGrayFill(0.92f);
				// cell300.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell300);

				PdfPCell cell301 = new PdfPCell(new Paragraph("Type of Telecom lines:", font11N));
				// cell301.setBorder(PdfPCell.NO_BORDER);
				cell301.setGrayFill(0.92f);
				cell301.setFixedHeight(25f);
				table4.addCell(cell301);
				PdfPCell cell302 = new PdfPCell(new Paragraph(structureAttributes.getTypeOfTelecomLines(), font11N));
				cell302.setGrayFill(0.92f);
				// cell302.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell302);

				PdfPCell cell303 = new PdfPCell(new Paragraph("Length of Telecom line (In meters):", font11N));
				// cell303.setBorder(PdfPCell.NO_BORDER);
				// cell303.setGrayFill(0.92f);
				cell303.setFixedHeight(25f);
				table4.addCell(cell303);
				PdfPCell cell304 = new PdfPCell(new Paragraph(structureAttributes.getLengthOfTelecomLines(), font11N));
				// cell304.setGrayFill(0.92f);
				// cell304.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell304);

				PdfPCell cell305 = new PdfPCell(new Paragraph("Shielding, grounding, isolation:", font11N));
				// cell305.setBorder(PdfPCell.NO_BORDER);
				cell305.setGrayFill(0.92f);
				cell305.setFixedHeight(25f);
				table4.addCell(cell305);
				PdfPCell cell306 = new PdfPCell(
						new Paragraph(structureAttributes.getShieldingGroundingIsolationL1(), font11N));
				cell306.setGrayFill(0.92f);
				// cell306.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell306);

				PdfPCell cell307 = new PdfPCell(new Paragraph("Collection area of the Telecom lines:", font11N));
				// cell307.setBorder(PdfPCell.NO_BORDER);
				// cell307.setGrayFill(0.92f);
				cell307.setFixedHeight(25f);
				table4.addCell(cell307);
				PdfPCell cell308 = new PdfPCell(
						new Paragraph(structureAttributes.getCollAreaOfTelecomLines(), font11N));
				// cell308.setGrayFill(0.92f);
				// cell308.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell308);

				PdfPCell cell309 = new PdfPCell(new Paragraph("Collection area near the Telecom lines:", font11N));
				// cell309.setBorder(PdfPCell.NO_BORDER);
				cell309.setGrayFill(0.92f);
				cell309.setFixedHeight(25f);
				table4.addCell(cell309);
				PdfPCell cell310 = new PdfPCell(
						new Paragraph(structureAttributes.getCollNearOfTelecomLines(), font11N));
				cell310.setGrayFill(0.92f);
				// cell310.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell310);

				PdfPCell cell311 = new PdfPCell(
						new Paragraph("No. of dangerous event near the Telecom lines:", font11N));
				// cell311.setBorder(PdfPCell.NO_BORDER);
				// cell311.setGrayFill(0.92f);
				cell311.setFixedHeight(25f);
				table4.addCell(cell311);
				PdfPCell cell312 = new PdfPCell(
						new Paragraph(structureAttributes.getEventNearTheTelecomeLines(), font11N));
				// cell312.setGrayFill(0.92f);
				// cell312.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell312);

				PdfPCell cell313 = new PdfPCell(new Paragraph("No. of dangerous event on the Telecom lines:", font11N));
				// cell313.setBorder(PdfPCell.NO_BORDER);
				cell313.setGrayFill(0.92f);
				cell313.setFixedHeight(25f);
				table4.addCell(cell313);
				PdfPCell cell314 = new PdfPCell(
						new Paragraph(structureAttributes.getEventOnTheTelecomLines(), font11N));
				cell314.setGrayFill(0.92f);
				// cell314.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell314);

				document.add(table4);

				PdfPTable table5 = new PdfPTable(pointColumnHeadLabel);
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(15f); // Space before table
//				SiteRFSourceTable.setSpacingAfter(5f); // Space after table

				PdfPCell dataCell6 = new PdfPCell(new Paragraph("LOSS OF HUMAN LIFE (L1)", font10B));
				dataCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
//				dataCell6.setBackgroundColor(new GrayColor(0.93f));
				dataCell6.setFixedHeight(25f);
				table5.addCell(dataCell6);
				document.add(table5);

				PdfPTable table6 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table6.setWidthPercentage(100); // Width 100%
				// table6.setSpacingBefore(10f); // Space before table
//				table6.setSpacingAfter(10f); // Space after table
				table6.getDefaultCell().setBorder(0);

				for (Losses losses : loss) {

					PdfPCell cell24 = new PdfPCell(new Paragraph("Hazard classification", font11N));
					// cell24.setBorder(PdfPCell.NO_BORDER);
					cell24.setGrayFill(0.92f);
					cell24.setFixedHeight(25f);
					table6.addCell(cell24);
					PdfPCell cell25 = new PdfPCell(new Paragraph(losses.getHazardClassification(), font11N));
					cell25.setGrayFill(0.92f);
					// cell25.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell25);

					PdfPCell cell132 = new PdfPCell(new Paragraph("Loss due to Physical damage:", font11N));
					// cell132.setBorder(PdfPCell.NO_BORDER);
					// cell132.setGrayFill(0.92f);
					cell132.setFixedHeight(25f);
					table6.addCell(cell132);
					PdfPCell cell133 = new PdfPCell(new Paragraph(losses.getHumanLossOfphysicalDamage(), font11N));
					// cell133.setGrayFill(0.92f);
					// cell133.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell133);

					PdfPCell cell134 = new PdfPCell(new Paragraph("Loss due to failure of internal system :", font11N));
					// cell134.setBorder(PdfPCell.NO_BORDER);
					cell134.setGrayFill(0.92f);
					cell134.setFixedHeight(25f);
					table6.addCell(cell134);
					PdfPCell cell135 = new PdfPCell(
							new Paragraph(losses.getHumanLossOffailureOfInternalSystem(), font11N));
					cell135.setGrayFill(0.92f);
					// cell135.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell135);

					PdfPCell cell136 = new PdfPCell(
							new Paragraph("Loss due to injury to living beings by electric shock:", font11N));
					// cell136.setBorder(PdfPCell.NO_BORDER);
					// cell136.setGrayFill(0.92f);
					cell136.setFixedHeight(25f);
					table6.addCell(cell136);
					PdfPCell cell137 = new PdfPCell(
							new Paragraph(losses.getHumanLossOfInjuryOfElectricShock(), font11N));
					// cell137.setGrayFill(0.92f);
					// cell137.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell137);

					PdfPCell cell1361 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
					// cell1361.setBorder(PdfPCell.NO_BORDER);
					cell1361.setGrayFill(0.92f);
					cell1361.setFixedHeight(25f);
					table6.addCell(cell1361);
					PdfPCell cell1371 = new PdfPCell(new Paragraph(losses.getHumanLossOfPhysicalDamageL1(), font11N));
					cell1371.setGrayFill(0.92f);
					// cell1371.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell1371);

					PdfPCell cell1362 = new PdfPCell(
							new Paragraph("Loss due to failure of internal systems:", font11N));
					// cell1362.setBorder(PdfPCell.NO_BORDER);
					// cell1362.setGrayFill(0.92f);
					cell1362.setFixedHeight(25f);
					table6.addCell(cell1362);
					PdfPCell cell1373 = new PdfPCell(
							new Paragraph(losses.getHumanLossOffailureOfInternalSystemL1(), font11N));
					// cell1373.setGrayFill(0.92f);
					// cell1373.setBorder(PdfPCell.NO_BORDER);
					table6.addCell(cell1373);
					document.add(table6);

					PdfPTable tableData6 = new PdfPTable(pointColumnHeadLabel);
					tableData6.setWidthPercentage(100); // Width 100%
					tableData6.setSpacingBefore(15f); // Space before table
//				tableData6.setSpacingAfter(5f); // Space after table

					PdfPCell dataCell8 = new PdfPCell(new Paragraph("LOSS OF SERVICE TO PUBLIC (L2)", font10B));
					dataCell8.setHorizontalAlignment(Element.ALIGN_CENTER);
//				dataCell8.setBackgroundColor(new GrayColor(0.93f));
					dataCell8.setFixedHeight(25f);
					tableData6.addCell(dataCell8);
					document.add(tableData6);

					PdfPTable table7 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table7.setWidthPercentage(100); // Width 100%
					// table7.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
					table7.getDefaultCell().setBorder(0);

					PdfPCell cell701 = new PdfPCell(new Paragraph("Loss due to Physical damage:", font11N));
					// cell701.setBorder(PdfPCell.NO_BORDER);
					cell701.setGrayFill(0.92f);
					cell701.setFixedHeight(25f);
					table7.addCell(cell701);
					PdfPCell cell712 = new PdfPCell(new Paragraph(losses.getSerToPubPhysicalDamage(), font11N));
					cell712.setGrayFill(0.92f);
					// cell712.setBorder(PdfPCell.NO_BORDER);
					table7.addCell(cell712);

					PdfPCell cell713 = new PdfPCell(new Paragraph("Loss due to failure of internal system:", font11N));
					// cell713.setBorder(PdfPCell.NO_BORDER);
					// cell713.setGrayFill(0.92f);
					cell713.setFixedHeight(25f);
					table7.addCell(cell713);
					PdfPCell cell432 = new PdfPCell(
							new Paragraph(losses.getSerToPubfailureOfInternalSystem(), font11N));
					// cell432.setGrayFill(0.92f);
					// cell432.setBorder(PdfPCell.NO_BORDER);
					table7.addCell(cell432);

					PdfPCell cell7012 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
					// cell7012.setBorder(PdfPCell.NO_BORDER);
					cell7012.setGrayFill(0.92f);
					cell7012.setFixedHeight(25f);
					table7.addCell(cell7012);
					PdfPCell cell7127 = new PdfPCell(new Paragraph(losses.getSerToPubPhysicalDamageL1(), font11N));
					cell7127.setGrayFill(0.92f);
					// cell7127.setBorder(PdfPCell.NO_BORDER);
					table7.addCell(cell7127);

					PdfPCell cell7013 = new PdfPCell(
							new Paragraph("Loss due to failure of internal systems:", font11N));
					// cell7013.setBorder(PdfPCell.NO_BORDER);
					// cell7013.setGrayFill(0.92f);
					cell7013.setFixedHeight(25f);
					table7.addCell(cell7013);
					PdfPCell cell7123 = new PdfPCell(
							new Paragraph(losses.getSerToPubfailureOfInternalSystemL1(), font11N));
					// cell7123.setGrayFill(0.92f);
					// cell7123.setBorder(PdfPCell.NO_BORDER);
					table7.addCell(cell7123);

					document.add(table7);

					PdfPTable table18 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table18.setWidthPercentage(100); // Width 100%
					// table18.setSpacingBefore(10f); // Space before table
//					table14.setSpacingAfter(10f); // Space after table
					table18.getDefaultCell().setBorder(0);

					PdfPTable table9 = new PdfPTable(pointColumnHeadLabel);
					table9.setWidthPercentage(100); // Width 100%
					table9.setSpacingBefore(15f); // Space before table
//					table9.setSpacingAfter(5f); // Space after table

					PdfPCell tableData9 = new PdfPCell(new Paragraph("LOSS OF CULTURAL HERITAGE (L3)", font10B));
					tableData9.setHorizontalAlignment(Element.ALIGN_CENTER);
//					tableData9.setBackgroundColor(new GrayColor(0.93f));
					tableData9.setFixedHeight(25f);
					table9.addCell(tableData9);
					document.add(table9);

					PdfPTable table21 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table21.setWidthPercentage(100); // Width 100%
					// table21.setSpacingBefore(5f); // Space before table
//					table21.setSpacingAfter(10f); // Space after table
					table21.getDefaultCell().setBorder(0);

					PdfPCell cell7018 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
					// cell7018.setBorder(PdfPCell.NO_BORDER);
					// cell7018.setGrayFill(0.92f);
					cell7018.setFixedHeight(25f);
					table21.addCell(cell7018);
					PdfPCell cell7129 = new PdfPCell(new Paragraph(losses.getCulHerOfPhysicalDamage(), font11N));
					// cell7129.setGrayFill(0.92f);
					// cell7129.setBorder(PdfPCell.NO_BORDER);
					table21.addCell(cell7129);

					PdfPCell cell728 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
					// cell728.setBorder(PdfPCell.NO_BORDER);
					cell728.setGrayFill(0.92f);
					cell728.setFixedHeight(25f);
					table21.addCell(cell728);
					PdfPCell cell731 = new PdfPCell(new Paragraph(losses.getCulHerOfPhysicalDamageL1(), font11N));
					cell731.setGrayFill(0.92f);
					// cell731.setBorder(PdfPCell.NO_BORDER);
					table21.addCell(cell731);

					document.add(table21);

					PdfPTable table19 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table19.setWidthPercentage(100); // Width 100%
					// table19.setSpacingBefore(10f); // Space before table
//					table19.setSpacingAfter(10f); // Space after table
					table19.getDefaultCell().setBorder(0);

					PdfPTable table98 = new PdfPTable(pointColumnHeadLabel);
					table98.setWidthPercentage(100); // Width 100%
					table98.setSpacingBefore(15f); // Space before table
//					table98.setSpacingAfter(5f); // Space after table

					PdfPCell tableData96 = new PdfPCell(new Paragraph("ECONOMIC LOSS (L4)", font10B));
					tableData96.setHorizontalAlignment(Element.ALIGN_CENTER);
//					tableData96.setBackgroundColor(new GrayColor(0.93f));
					tableData96.setFixedHeight(25f);
					table98.addCell(tableData96);
					document.add(table98);

					PdfPCell cell7281 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
//					cell7281.setBorder(PdfPCell.NO_BORDER);
					cell7281.setGrayFill(0.92f);
					cell7281.setFixedHeight(25f);
					table19.addCell(cell7281);
					PdfPCell cell7321 = new PdfPCell(new Paragraph(losses.getEcoLossOfPhysicalDamage(), font11N));
					cell7321.setGrayFill(0.92f);
					// cell7321.setBorder(PdfPCell.NO_BORDER);
					table19.addCell(cell7321);

					PdfPCell cell72813 = new PdfPCell(
							new Paragraph("Loss due to failure of internal system:", font11N));
					// cell72813.setBorder(PdfPCell.NO_BORDER);
					// cell72813.setGrayFill(0.92f);
					cell72813.setFixedHeight(25f);
					table19.addCell(cell72813);
					PdfPCell cell73214 = new PdfPCell(
							new Paragraph(losses.getEcoLossOfFailureOfInternalSystem(), font11N));
					// cell73214.setGrayFill(0.92f);
					// cell73214.setBorder(PdfPCell.NO_BORDER);
					table19.addCell(cell73214);

					PdfPCell cell7283 = new PdfPCell(
							new Paragraph("Loss due to injury to living beings by electric:", font11N));
					// cell7283.setBorder(PdfPCell.NO_BORDER);
					cell7283.setGrayFill(0.92f);
					cell7283.setFixedHeight(25f);
					table19.addCell(cell7283);
					PdfPCell cell732 = new PdfPCell(new Paragraph(losses.getEcoLossOfInjuryOfElectricShock(), font11N));
					cell732.setGrayFill(0.92f);
					// cell732.setBorder(PdfPCell.NO_BORDER);
					table19.addCell(cell732);

					PdfPCell cell729 = new PdfPCell(new Paragraph("Loss due to physical damage:", font11N));
					// cell729.setBorder(PdfPCell.NO_BORDER);
					// cell729.setGrayFill(0.92f);
					cell729.setFixedHeight(25f);
					table19.addCell(cell729);
					PdfPCell cell7320 = new PdfPCell(new Paragraph(losses.getEcoLossOfPhysicalDamageL1(), font11N));
					// cell7320.setGrayFill(0.92f);
					// cell7320.setBorder(PdfPCell.NO_BORDER);
					table19.addCell(cell7320);

					PdfPCell cell72835 = new PdfPCell(
							new Paragraph("Loss due to failure of internal systems:", font11N));
					// cell72835.setBorder(PdfPCell.NO_BORDER);
					cell72835.setGrayFill(0.92f);
					cell72835.setFixedHeight(25f);
					table19.addCell(cell72835);
					PdfPCell cell73212 = new PdfPCell(
							new Paragraph(losses.getEcoLossOfFailureOfInternalSystemL1(), font11N));
					cell73212.setGrayFill(0.92f);
					// cell73212.setBorder(PdfPCell.NO_BORDER);
					table19.addCell(cell73212);

					document.add(table19);

// Protect Part Starts Here		

					document.newPage();

					PdfPTable protectionHead = new PdfPTable(pointColumnHeadLabel);
					protectionHead.setWidthPercentage(100); // Width 100%
					protectionHead.setSpacingBefore(15f); // Space before table
//					protectionHead.setSpacingAfter(5f); // Space after table

					PdfPCell protectionLabel = new PdfPCell(new Paragraph("PROTECTION ", protectedValues10B));
					protectionLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//					protectionLabel.setBackgroundColor(BaseColor.GREEN);
					protectionLabel.setFixedHeight(25f);
					protectionHead.addCell(protectionLabel);
					document.add(protectionHead);

					PdfPTable protectionTable = new PdfPTable(pointColumnWidths1); // 3 columns.
					protectionTable.setWidthPercentage(100); // Width 100%
//					protectionTable.setSpacingBefore(5f); // Space before table
//		     		protectionTable.setSpacingAfter(10f); // Space after table
					protectionTable.getDefaultCell().setBorder(0);
                   
					for (Protection protection : protect) {
						for (RiskProtection riskprotection : riskprotect) {
							for (CalculatedRisk calculatedRisk : calculated) {
								
								String lossOfHumanLifeRT1 = calculatedRisk.getLossOfHumanLifeRT1();
								String lossOfHumanPublicServiceRT2 = calculatedRisk.getLossOfPublicSerivceRT2();
								String lossOfCulturalHeritageRT3 = calculatedRisk.getLossOfCulturalHeritageRT3();
								String lossOfEconomicRT4 = calculatedRisk.getEconomicLossRT4();
								
								if(lossOfHumanLifeRT1.equalsIgnoreCase("1.00E-05")) {
									lossOfHumanLifeRT1 = "0.0000100";
								}
								if(lossOfHumanPublicServiceRT2.equalsIgnoreCase("1.00E-03")) {
									lossOfHumanPublicServiceRT2 = "0.001";
								}
								if(lossOfCulturalHeritageRT3.equalsIgnoreCase("1.00E-04")) {
									lossOfCulturalHeritageRT3 = "0.0001";
								}
								if(lossOfEconomicRT4.equalsIgnoreCase("1.00E-03")) {
									lossOfEconomicRT4 = "0.001";
								}
								boolean rp1 = riskProtectionValuesHumanLife(calculatedRisk, lossOfHumanLifeRT1);
								boolean rp2 = riskProtectionForPublicServiceLoss(calculatedRisk, lossOfHumanPublicServiceRT2);
								boolean rp3 = riskProtectionForCulturalHeritageLoss(calculatedRisk, lossOfCulturalHeritageRT3);
								boolean rp4 = riskProtectionForEconomicLoss(calculatedRisk, lossOfEconomicRT4);

								PdfPCell cell82 = new PdfPCell(new Paragraph("Class of LPS :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
								cell82.setFixedHeight(25f);
								protectionTable.addCell(cell82);
								PdfPCell cell83 = new PdfPCell(new Paragraph(losses.getClassOfLPS(), font11N));
								protectionTable.addCell(cell83);

								PdfPCell cell84 = new PdfPCell(new Paragraph("Class of SPD :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
								cell84.setFixedHeight(25f);
								protectionTable.addCell(cell84);
								PdfPCell cell85 = new PdfPCell(new Paragraph(losses.getClassOfSPD(), font11N));
								protectionTable.addCell(cell85);

								if (losses.getClassOfSPD() != null) {
									PdfPCell cell86 = new PdfPCell(new Paragraph("PEB :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell86.setFixedHeight(25f);
									protectionTable.addCell(cell86);
									String pebValue = null;
									switch (losses.getClassOfSPD()) {
									case "No SPD": 
										pebValue = "1";
										break;
									case "Protec T1H 300 3+1 R":
										pebValue = "0.05";
										break;
									case "Protec T1HS 300 3+1 R":
										pebValue = "0.01";
										break;
									case "Protec T1HS 300 3 + 1 R & Protec T2H 300 3 + 1":
										pebValue = "0.005";
									}

									PdfPCell cell87 = new PdfPCell(new Paragraph(((pebValue != null) ? pebValue : ""), font11N));
									protectionTable.addCell(cell87);
								} else {
									PdfPCell cell86 = new PdfPCell(new Paragraph("PEB :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell86.setFixedHeight(25f);
									protectionTable.addCell(cell86);
									PdfPCell cell87 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell87);
								}

								if (protection.getProtectionPMS() != null) {
									PdfPCell cell88 = new PdfPCell(new Paragraph("PMS :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell88.setFixedHeight(25f);
									protectionTable.addCell(cell88);
									PdfPCell cell89 = new PdfPCell(
											new Paragraph(protection.getProtectionPMS(), font11N));
									protectionTable.addCell(cell89);
								} else {
									PdfPCell cell88 = new PdfPCell(new Paragraph("PMS :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell88.setFixedHeight(25f);
									protectionTable.addCell(cell88);
									PdfPCell cell89 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell89);
								}

								if (protection.getProtectionPM() != null) {
									PdfPCell cell90 = new PdfPCell(new Paragraph("PM :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell90.setFixedHeight(25f);
									protectionTable.addCell(cell90);
									PdfPCell cell91 = new PdfPCell(
											new Paragraph(protection.getProtectionPM(), font11N));
									protectionTable.addCell(cell91);
								} else {
									PdfPCell cell90 = new PdfPCell(new Paragraph("PM :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell90.setFixedHeight(25f);
									protectionTable.addCell(cell90);
									PdfPCell cell91 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell91);
								}

								if (protection.getProtectionPA() != null) {
									PdfPCell cell92 = new PdfPCell(new Paragraph("PA :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell92.setFixedHeight(25f);
									protectionTable.addCell(cell92);
									PdfPCell cell93 = new PdfPCell(
											new Paragraph(protection.getProtectionPA(), font11N));
									protectionTable.addCell(cell93);
								} else {
									PdfPCell cell92 = new PdfPCell(new Paragraph("PA :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell92.setFixedHeight(25f);
									protectionTable.addCell(cell92);
									PdfPCell cell93 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell93);
								}

								if (protection.getProtectionPC() != null) {
									PdfPCell cell94 = new PdfPCell(new Paragraph("PC :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell94.setFixedHeight(25f);
									protectionTable.addCell(cell94);
									PdfPCell cell95 = new PdfPCell(
											new Paragraph(protection.getProtectionPC(), font11N));
									protectionTable.addCell(cell95);
								} else {
									PdfPCell cell94 = new PdfPCell(new Paragraph("PC :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell94.setFixedHeight(25f);
									protectionTable.addCell(cell94);
									PdfPCell cell95 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell95);
								}

								if (protection.getProtectionPU() != null) {
									PdfPCell cell96 = new PdfPCell(new Paragraph("PU :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell96.setFixedHeight(25f);
									protectionTable.addCell(cell96);
									PdfPCell cell97 = new PdfPCell(
											new Paragraph(protection.getProtectionPU(), font11N));
									protectionTable.addCell(cell97);
								} else {
									PdfPCell cell96 = new PdfPCell(new Paragraph("PU :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell96.setFixedHeight(25f);
									protectionTable.addCell(cell96);
									PdfPCell cell97 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell97);
								}

								if (protection.getProtectionPV() != null) {
									PdfPCell cell98 = new PdfPCell(new Paragraph("PV :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell98.setFixedHeight(25f);
									protectionTable.addCell(cell98);
									PdfPCell cell99 = new PdfPCell(
											new Paragraph(protection.getProtectionPC(), font11N));
									protectionTable.addCell(cell99);
								} else {
									PdfPCell cell98 = new PdfPCell(new Paragraph("PV :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell98.setFixedHeight(25f);
									protectionTable.addCell(cell98);
									PdfPCell cell99 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell99);
								}

								if (protection.getProtectionPW() != null) {
									PdfPCell cell100 = new PdfPCell(new Paragraph("PW :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell100.setFixedHeight(25f);
									protectionTable.addCell(cell100);
									PdfPCell cell101 = new PdfPCell(
											new Paragraph(protection.getProtectionPW(), font11N));
									protectionTable.addCell(cell101);
								} else {
									PdfPCell cell100 = new PdfPCell(new Paragraph("PW :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell100.setFixedHeight(25f);
									protectionTable.addCell(cell100);
									PdfPCell cell101 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell101);
								}

								if (protection.getProtectionPZ() != null) {
									PdfPCell cell102 = new PdfPCell(new Paragraph("PZ :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell102.setFixedHeight(25f);
									protectionTable.addCell(cell102);
									PdfPCell cell103 = new PdfPCell(
											new Paragraph(protection.getProtectionPZ(), font11N));
									protectionTable.addCell(cell103);
								} else {
									PdfPCell cell102 = new PdfPCell(new Paragraph("PZ :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell102.setFixedHeight(25f);
									protectionTable.addCell(cell102);
									PdfPCell cell103 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell103);
								}

								if (protection.getRiskProtectionRA1() != null) {
									PdfPCell cell104 = new PdfPCell(new Paragraph("RA1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell104.setFixedHeight(25f);
									protectionTable.addCell(cell104);
									PdfPCell cell105 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRA1(), font11N));
									protectionTable.addCell(cell105);
								} else {
									PdfPCell cell104 = new PdfPCell(new Paragraph("RA1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell104.setFixedHeight(25f);
									protectionTable.addCell(cell104);
									PdfPCell cell105 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell105);
								}

								if (protection.getRiskProtectionRB1() != null) {
									PdfPCell cell106 = new PdfPCell(new Paragraph("RB1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell106.setFixedHeight(25f);
									protectionTable.addCell(cell106);
									PdfPCell cell107 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRB1(), font11N));
									protectionTable.addCell(cell107);
								} else {
									PdfPCell cell106 = new PdfPCell(new Paragraph("RB1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell106.setFixedHeight(25f);
									protectionTable.addCell(cell106);
									PdfPCell cell107 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell107);
								}

								if (protection.getRiskProtectionRC1() != null) {
									PdfPCell cell108 = new PdfPCell(new Paragraph("RC1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell108.setFixedHeight(25f);
									protectionTable.addCell(cell108);
									PdfPCell cell109 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRC1(), font11N));
									protectionTable.addCell(cell109);
								} else {
									PdfPCell cell108 = new PdfPCell(new Paragraph("RC1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell108.setFixedHeight(25f);
									protectionTable.addCell(cell108);
									PdfPCell cell109 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell109);
								}

								if (protection.getRiskProtectionRM1() != null) {
									PdfPCell cell124 = new PdfPCell(new Paragraph("RM1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell124.setFixedHeight(25f);
									protectionTable.addCell(cell124);
									PdfPCell cell125 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRM1(), font11N));
									protectionTable.addCell(cell125);
								} else {
									PdfPCell cell124 = new PdfPCell(new Paragraph("RM1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell124.setFixedHeight(25f);
									protectionTable.addCell(cell124);
									PdfPCell cell125 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell125);
								}

								if (protection.getRiskProtectionRU1() != null) {
									PdfPCell cell128 = new PdfPCell(new Paragraph("RU1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell128.setFixedHeight(25f);
									protectionTable.addCell(cell128);
									PdfPCell cell129 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRU1(), font11N));
									protectionTable.addCell(cell129);
								} else {
									PdfPCell cell128 = new PdfPCell(new Paragraph("RU1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell128.setFixedHeight(25f);
									protectionTable.addCell(cell128);
									PdfPCell cell129 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell129);
								}

								if (protection.getRiskProtectionRV1() != null) {
									PdfPCell cell130 = new PdfPCell(new Paragraph("RV1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell130.setFixedHeight(25f);
									protectionTable.addCell(cell130);
									PdfPCell cell131 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRV1(), font11N));
									protectionTable.addCell(cell131);
								} else {
									PdfPCell cell130 = new PdfPCell(new Paragraph("RV1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell130.setFixedHeight(25f);
									protectionTable.addCell(cell130);
									PdfPCell cell131 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell131);
								}

								if (protection.getRiskProtectionRW1() != null) {
									PdfPCell cell140 = new PdfPCell(new Paragraph("RW1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell140.setFixedHeight(25f);
									protectionTable.addCell(cell140);
									PdfPCell cell141 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRW1(), font11N));
									protectionTable.addCell(cell141);
								} else {
									PdfPCell cell140 = new PdfPCell(new Paragraph("RW1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell140.setFixedHeight(25f);
									protectionTable.addCell(cell140);
									PdfPCell cell141 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell141);
								}

								if (protection.getRiskProtectionRZ1() != null) {
									PdfPCell cell142 = new PdfPCell(new Paragraph("RZ1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell142.setFixedHeight(25f);
									protectionTable.addCell(cell142);
									PdfPCell cell143 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRZ1(), font11N));
									protectionTable.addCell(cell143);
								} else {
									PdfPCell cell142 = new PdfPCell(new Paragraph("RZ1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell142.setFixedHeight(25f);
									protectionTable.addCell(cell142);
									PdfPCell cell143 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell143);
								}

								if (protection.getRiskProtectionRV1() != null) {
									PdfPCell cell144 = new PdfPCell(new Paragraph("RV1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell144.setFixedHeight(25f);
									protectionTable.addCell(cell144);
									PdfPCell cell146 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRV1(), font11N));
									protectionTable.addCell(cell146);
								} else {
									PdfPCell cell144 = new PdfPCell(new Paragraph("RV1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell144.setFixedHeight(25f);
									protectionTable.addCell(cell144);
									PdfPCell cell146 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell146);
								}

								if (calculatedRisk.getRiskProtectionRD1() != null) {
									PdfPCell cell147 = new PdfPCell(new Paragraph("RD1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell147.setFixedHeight(25f);
									protectionTable.addCell(cell147);
									PdfPCell cell148 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRD1(), font11N));
									protectionTable.addCell(cell148);
								} else {
									PdfPCell cell147 = new PdfPCell(new Paragraph("RD1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell147.setFixedHeight(25f);
									protectionTable.addCell(cell147);
									PdfPCell cell148 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell148);
								}

								if (calculatedRisk.getRiskProtectionRI1() != null) {
									PdfPCell cell149 = new PdfPCell(new Paragraph("RI1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell149.setFixedHeight(25f);
									protectionTable.addCell(cell149);
									PdfPCell cell150 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRI1(), font11N));
									protectionTable.addCell(cell150);
								} else {
									PdfPCell cell149 = new PdfPCell(new Paragraph("RI1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell149.setFixedHeight(25f);
									protectionTable.addCell(cell149);
									PdfPCell cell150 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell150);
								}

								if (calculatedRisk.getRiskProtectionR1() != null) {
									PdfPCell cell151 = new PdfPCell(new Paragraph("R1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell151.setFixedHeight(25f);
									protectionTable.addCell(cell151);
									PdfPCell cell152 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionR1(), font11N));
									protectionTable.addCell(cell152);
								} else {
									PdfPCell cell151 = new PdfPCell(new Paragraph("R1 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell151.setFixedHeight(25f);
									protectionTable.addCell(cell151);
									PdfPCell cell152 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell152);

								}

								if (protection.getRiskProtectionRB2() != null) {
									PdfPCell cell156 = new PdfPCell(new Paragraph("RB2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell156.setFixedHeight(25f);
									protectionTable.addCell(cell156);
									PdfPCell cell157 = new PdfPCell(
											new Paragraph(protection.getRiskProtectionRB2(), font11N));
									protectionTable.addCell(cell157);
								} else {
									PdfPCell cell156 = new PdfPCell(new Paragraph("RB2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell156.setFixedHeight(25f);
									protectionTable.addCell(cell156);
									PdfPCell cell157 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell157);
								}

//	RiskProtection Section

								if (riskprotection.getRiskProtectionRC2() != null) {
									PdfPCell cell158 = new PdfPCell(new Paragraph("RC2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell158.setFixedHeight(25f);
									protectionTable.addCell(cell158);
									PdfPCell cell159 = new PdfPCell(
											new Paragraph(riskprotection.getRiskProtectionRC2(), font11N));
									protectionTable.addCell(cell159);
								} else {
									PdfPCell cell158 = new PdfPCell(new Paragraph("RC2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell158.setFixedHeight(25f);
									protectionTable.addCell(cell158);
									PdfPCell cell159 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell159);
								}

								if (riskprotection.getRiskProtectionRM2() != null) {
									PdfPCell cell160 = new PdfPCell(new Paragraph("RM2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell160.setFixedHeight(25f);
									protectionTable.addCell(cell160);
									PdfPCell cell161 = new PdfPCell(
											new Paragraph(riskprotection.getRiskProtectionRM2(), font11N));
									protectionTable.addCell(cell161);
								} else {
									PdfPCell cell160 = new PdfPCell(new Paragraph("RM2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell160.setFixedHeight(25f);
									protectionTable.addCell(cell160);
									PdfPCell cell161 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell161);
								}

								if (riskprotection.getRiskProtectionRV2() != null) {
									PdfPCell cell162 = new PdfPCell(new Paragraph("RV2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell162.setFixedHeight(25f);
									protectionTable.addCell(cell162);
									PdfPCell cell163 = new PdfPCell(
											new Paragraph(riskprotection.getRiskProtectionRV2(), font11N));
									protectionTable.addCell(cell163);
								} else {
									PdfPCell cell162 = new PdfPCell(new Paragraph("RV2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell162.setFixedHeight(25f);
									protectionTable.addCell(cell162);
									PdfPCell cell163 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell163);
								}

								if (riskprotection.getRiskProtectionRW2() != null) {
									PdfPCell cell164 = new PdfPCell(new Paragraph("RW2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell164.setFixedHeight(25f);
									protectionTable.addCell(cell164);
									PdfPCell cell165 = new PdfPCell(
											new Paragraph(riskprotection.getRiskProtectionRW2(), font11N));
									protectionTable.addCell(cell165);
								} else {
									PdfPCell cell164 = new PdfPCell(new Paragraph("RW2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell164.setFixedHeight(25f);
									protectionTable.addCell(cell164);
									PdfPCell cell165 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell165);
								}

								if (riskprotection.getRiskProtectionRZ2() != null) {
									PdfPCell cell166 = new PdfPCell(new Paragraph("RZ2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell166.setFixedHeight(25f);
									protectionTable.addCell(cell166);
									PdfPCell cell167 = new PdfPCell(
											new Paragraph(riskprotection.getRiskProtectionRZ2(), font11N));
									protectionTable.addCell(cell167);
								} else {
									PdfPCell cell166 = new PdfPCell(new Paragraph("RZ2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell166.setFixedHeight(25f);
									protectionTable.addCell(cell166);
									PdfPCell cell167 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell167);
								}

//	 Calculated Section 						

								if (calculatedRisk.getRiskProtectionRD2() != null) {
									PdfPCell cell168 = new PdfPCell(new Paragraph("RD2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell168.setFixedHeight(25f);
									protectionTable.addCell(cell168);
									PdfPCell cell169 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRD2(), font11N));
									protectionTable.addCell(cell169);
								} else {
									PdfPCell cell168 = new PdfPCell(new Paragraph("RD2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell168.setFixedHeight(25f);
									protectionTable.addCell(cell168);
									PdfPCell cell169 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell169);
								}

								if (calculatedRisk.getRiskProtectionRI2() != null) {
									PdfPCell cell170 = new PdfPCell(new Paragraph("RI2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell170.setFixedHeight(25f);
									protectionTable.addCell(cell170);
									PdfPCell cell171 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRI2(), font11N));
									protectionTable.addCell(cell171);
								} else {
									PdfPCell cell170 = new PdfPCell(new Paragraph("RI2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell170.setFixedHeight(25f);
									protectionTable.addCell(cell170);
									PdfPCell cell171 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell171);
								}

								if (calculatedRisk.getRiskProtectionR2() != null) {
									PdfPCell cell172 = new PdfPCell(new Paragraph("R2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell172.setFixedHeight(25f);
									protectionTable.addCell(cell172);
									PdfPCell cell173 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionR2(), font11N));
									protectionTable.addCell(cell173);
								} else {
									PdfPCell cell172 = new PdfPCell(new Paragraph("R2 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell172.setFixedHeight(25f);
									protectionTable.addCell(cell172);
									PdfPCell cell173 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell173);
								}

								if (protection.getCulturalRB() != null) {
									PdfPCell cell174 = new PdfPCell(new Paragraph("RB3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell174.setFixedHeight(25f);
									protectionTable.addCell(cell174);
									PdfPCell cell175 = new PdfPCell(new Paragraph(protection.getCulturalRB(), font11N));
									protectionTable.addCell(cell175);
								} else {
									PdfPCell cell174 = new PdfPCell(new Paragraph("RB3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell174.setFixedHeight(25f);
									protectionTable.addCell(cell174);
									PdfPCell cell175 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell175);
								}

								if (protection.getCulturalRV() != null) {
									PdfPCell cell176 = new PdfPCell(new Paragraph("RV3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell176.setFixedHeight(25f);
									protectionTable.addCell(cell176);
									PdfPCell cell177 = new PdfPCell(new Paragraph(protection.getCulturalRV(), font11N));
									protectionTable.addCell(cell177);
								} else {
									PdfPCell cell176 = new PdfPCell(new Paragraph("RV3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell176.setFixedHeight(25f);
									protectionTable.addCell(cell176);
									PdfPCell cell177 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell177);
								}

								if (calculatedRisk.getRiskProtectionRD3() != null) {
									PdfPCell cell178 = new PdfPCell(new Paragraph("RD3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell178.setFixedHeight(25f);
									protectionTable.addCell(cell178);
									PdfPCell cell179 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRD3(), font11N));
									protectionTable.addCell(cell179);
								} else {
									PdfPCell cell178 = new PdfPCell(new Paragraph("RD3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell178.setFixedHeight(25f);
									protectionTable.addCell(cell178);
									PdfPCell cell179 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell179);
								}

								if (calculatedRisk.getRiskProtectionR3() != null) {
									PdfPCell cell180 = new PdfPCell(new Paragraph("R3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell180.setFixedHeight(25f);
									protectionTable.addCell(cell180);
									PdfPCell cell181 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionR3(), font11N));
									protectionTable.addCell(cell181);
								} else {
									PdfPCell cell180 = new PdfPCell(new Paragraph("R3 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell180.setFixedHeight(25f);
									protectionTable.addCell(cell180);
									PdfPCell cell181 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell181);
								}

								if (riskprotection.getEconamicValueRA() != null) {
									PdfPCell cell183 = new PdfPCell(new Paragraph("RA4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell183.setFixedHeight(25f);
									protectionTable.addCell(cell183);
									PdfPCell cell184 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRA(), font11N));
									protectionTable.addCell(cell184);
								} else {
									PdfPCell cell183 = new PdfPCell(new Paragraph("RA4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell183.setFixedHeight(25f);
									protectionTable.addCell(cell183);
									PdfPCell cell184 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell184);
								}

								if (riskprotection.getEconamicValueRB() != null) {
									PdfPCell cell185 = new PdfPCell(new Paragraph("RB4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell185.setFixedHeight(25f);
									protectionTable.addCell(cell185);
									PdfPCell cell186 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRB(), font11N));
									protectionTable.addCell(cell186);
								} else {
									PdfPCell cell185 = new PdfPCell(new Paragraph("RB4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell185.setFixedHeight(25f);
									protectionTable.addCell(cell185);
									PdfPCell cell186 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell186);
								}

								if (riskprotection.getEconamicValueRC() != null) {
									PdfPCell cell187 = new PdfPCell(new Paragraph("RC4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell187.setFixedHeight(25f);
									protectionTable.addCell(cell187);
									PdfPCell cell188 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRC(), font11N));
									protectionTable.addCell(cell188);
								} else {
									PdfPCell cell187 = new PdfPCell(new Paragraph("RC4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell187.setFixedHeight(25f);
									protectionTable.addCell(cell187);
									PdfPCell cell188 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell188);
								}

								if (riskprotection.getEconamicValueRM() != null) {
									PdfPCell cell189 = new PdfPCell(new Paragraph("RM4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell189.setFixedHeight(25f);
									protectionTable.addCell(cell189);
									PdfPCell cell190 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRM(), font11N));
									protectionTable.addCell(cell190);
								} else {
									PdfPCell cell189 = new PdfPCell(new Paragraph("RM4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell189.setFixedHeight(25f);
									protectionTable.addCell(cell189);
									PdfPCell cell190 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell190);
								}

								if (riskprotection.getEconamicValueRU() != null) {
									PdfPCell cell191 = new PdfPCell(new Paragraph("RU4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell191.setFixedHeight(25f);
									protectionTable.addCell(cell191);
									PdfPCell cell192 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRU(), font11N));
									protectionTable.addCell(cell192);
								} else {
									PdfPCell cell191 = new PdfPCell(new Paragraph("RU4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell191.setFixedHeight(25f);
									protectionTable.addCell(cell191);
									PdfPCell cell192 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell192);
								}

								if (riskprotection.getEconamicValueRV() != null) {
									PdfPCell cell193 = new PdfPCell(new Paragraph("RV4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell193.setFixedHeight(25f);
									protectionTable.addCell(cell193);
									PdfPCell cell194 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRV(), font11N));
									protectionTable.addCell(cell194);
								} else {
									PdfPCell cell193 = new PdfPCell(new Paragraph("RV4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell193.setFixedHeight(25f);
									protectionTable.addCell(cell193);
									PdfPCell cell194 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell194);
								}

								if (riskprotection.getEconamicValueRW() != null) {
									PdfPCell cell195 = new PdfPCell(new Paragraph("RW4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell195.setFixedHeight(25f);
									protectionTable.addCell(cell195);
									PdfPCell cell196 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRW(), font11N));
									protectionTable.addCell(cell196);
								} else {
									PdfPCell cell195 = new PdfPCell(new Paragraph("RW4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell195.setFixedHeight(25f);
									protectionTable.addCell(cell195);
									PdfPCell cell196 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell196);
								}

								if (riskprotection.getEconamicValueRZ() != null) {
									PdfPCell cell197 = new PdfPCell(new Paragraph("RZ4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell197.setFixedHeight(25f);
									protectionTable.addCell(cell197);
									PdfPCell cell198 = new PdfPCell(
											new Paragraph(riskprotection.getEconamicValueRZ(), font11N));
									protectionTable.addCell(cell198);
								} else {
									PdfPCell cell197 = new PdfPCell(new Paragraph("RZ4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell197.setFixedHeight(25f);
									protectionTable.addCell(cell197);
									PdfPCell cell198 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell198);
								}

								if (calculatedRisk.getRiskProtectionRD4() != null) {
									PdfPCell cell199 = new PdfPCell(new Paragraph("RD4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell199.setFixedHeight(25f);
									protectionTable.addCell(cell199);
									PdfPCell cell210 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRD4(), font11N));
									protectionTable.addCell(cell210);
								} else {
									PdfPCell cell199 = new PdfPCell(new Paragraph("RD4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell199.setFixedHeight(25f);
									protectionTable.addCell(cell199);
									PdfPCell cell210 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell210);
								}

								if (calculatedRisk.getRiskProtectionRI4() != null) {
									PdfPCell cell211 = new PdfPCell(new Paragraph("RI4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell211.setFixedHeight(25f);
									protectionTable.addCell(cell211);
									PdfPCell cell213 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionRI4(), font11N));
									protectionTable.addCell(cell213);
								} else {
									PdfPCell cell211 = new PdfPCell(new Paragraph("RI4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell211.setFixedHeight(25f);
									protectionTable.addCell(cell211);
									PdfPCell cell213 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell213);
								}

								if (calculatedRisk.getRiskProtectionR4() != null) {
									PdfPCell cell214 = new PdfPCell(new Paragraph("R4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell214.setFixedHeight(25f);
									protectionTable.addCell(cell214);
									PdfPCell cell215 = new PdfPCell(
											new Paragraph(calculatedRisk.getRiskProtectionR4(), font11N));
									protectionTable.addCell(cell215);
								} else {
									PdfPCell cell214 = new PdfPCell(new Paragraph("R4 :", rp1&rp2&rp3&rp4 ? protectedValues11N: unProtectedValues11N));
									cell214.setFixedHeight(25f);
									protectionTable.addCell(cell214);
									PdfPCell cell215 = new PdfPCell(new Paragraph("", font11N));
									protectionTable.addCell(cell215);
								}
								document.add(protectionTable);

								document.newPage();

								Paragraph paragraph1 = new Paragraph("5. CONCLUSION", font11B);
								paragraph1.setAlignment(Element.ALIGN_LEFT);
								document.add(paragraph1);

								PdfPTable conclusionHead = new PdfPTable(pointColumnHeadLabel);
								conclusionHead.setWidthPercentage(100); // Width 100%
								conclusionHead.setSpacingBefore(25f); // Space before table
//				conclusionHead.setSpacingAfter(5f); // Space after table

								PdfPCell conclusionLabel = new PdfPCell(new Paragraph(
										"Based on the Data provided, following table depicts the comparison between Tolerable risk"
												+ "\r\n\r\nand calculated risk with protection.",
										font11N));
								conclusionLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclusionLabel.setBorder(PdfPCell.NO_BORDER);
								conclusionHead.addCell(conclusionLabel);
								document.add(conclusionHead);
								

								float[] pointColumnWidths2 = { 25F, 20F, 25F, 25F, 25F, };

								PdfPTable conclisionTable = new PdfPTable(pointColumnWidths2); // 3 columns.
								conclisionTable.setWidthPercentage(100); // Width 100%
								conclisionTable.setSpacingBefore(20f); // Space before table
								conclisionTable.setSpacingAfter(20f); // Space after table
								conclisionTable.getDefaultCell().setBorder(0);

								PdfPCell cell250 = new PdfPCell(new Paragraph("\r\nType of Losses ", font10B));
								cell250.setFixedHeight(40f);
								cell250.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell250);

								PdfPCell cell251 = new PdfPCell(new Paragraph("\r\nTolerable \r\n" + "Risk", font10B));
								cell251.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell251);

								PdfPCell cell252 = new PdfPCell(
										new Paragraph("\r\nDirect Strike \r\n" + "Risk RD", font10B));
								cell252.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell252);

								PdfPCell cell253 = new PdfPCell(
										new Paragraph("\r\nIndirect Strike\r\n" + "Risk RI", font10B));
								cell253.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell253);

								PdfPCell cell254 = new PdfPCell(
										new Paragraph("\r\nCalculated Risk \r\n" + "(R = RD + RI)", font10B));
								cell254.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell254);

								PdfPCell cell255 = new PdfPCell(new Paragraph("\r\nLoss of human life ", font11N));
								cell255.setFixedHeight(40f);
								cell255.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclisionTable.addCell(cell255);

								PdfPCell cell256 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getLossOfHumanLifeRT1(), font10N));
								cell256.setFixedHeight(40f);
								cell256.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell256);

								PdfPCell cell257 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRD1(), font10N));
								cell257.setFixedHeight(40f);
								cell257.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell257);

								PdfPCell cell258 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRI1(), font10N));
								cell258.setFixedHeight(40f);
								cell258.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell258);

								PdfPCell cell259 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionR1(), rp1 ? protectedValues10N: unProtectedValues10N));
								cell259.setFixedHeight(40f);
								cell259.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell259);

								PdfPCell cell260 = new PdfPCell(new Paragraph("\r\nLoss of public services ", font11N));
								cell260.setFixedHeight(40f);
								cell260.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclisionTable.addCell(cell260);

								PdfPCell cell261 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getLossOfPublicSerivceRT2(), font10N));
								cell261.setFixedHeight(40f);
								cell261.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell261);

								PdfPCell cell262 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRD2(), font10N));
								cell262.setFixedHeight(40f);
								cell262.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell262);

								PdfPCell cell263 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRI2(), font10N));
								cell263.setFixedHeight(40f);
								cell263.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell263);

								PdfPCell cell264 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionR2(), rp2 ? protectedValues10N: unProtectedValues10N));
								cell264.setFixedHeight(40f);
								cell264.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell264);

								PdfPCell cell265 = new PdfPCell(
										new Paragraph("\r\nLoss of cultural heritage ", font11N));
								cell265.setFixedHeight(40f);
								cell265.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclisionTable.addCell(cell265);

								PdfPCell cell266 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getLossOfCulturalHeritageRT3(), font10N));
								cell266.setFixedHeight(40f);
								cell266.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell266);

								PdfPCell cell267 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRD3(), font10N));
								cell267.setFixedHeight(40f);
								cell267.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell267);

								PdfPCell cell268 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRI3(), font10N));
								cell268.setFixedHeight(40f);
								cell268.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell268);

								PdfPCell cell269 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionR3(), rp3 ? protectedValues10N: unProtectedValues10N));
								cell269.setFixedHeight(40f);
								cell269.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell269);

								PdfPCell cell270 = new PdfPCell(new Paragraph("\r\nEconomic loss ", font11N));
								cell270.setFixedHeight(40f);
								cell270.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclisionTable.addCell(cell270);

								PdfPCell cell271 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getEconomicLossRT4(), font10N));
								cell271.setFixedHeight(40f);
								cell271.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell271);

								PdfPCell cell272 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRD4(), font10N));
								cell272.setFixedHeight(40f);
								cell272.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell272);

								PdfPCell cell273 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionRI4(), font10N));
								cell273.setFixedHeight(40f);
								cell273.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell273);

								PdfPCell cell274 = new PdfPCell(new Paragraph(" \r\n"+calculatedRisk.getRiskProtectionR4(), rp4 ? protectedValues10N: unProtectedValues10N));
								cell274.setFixedHeight(40f);
								cell274.setHorizontalAlignment(Element.ALIGN_CENTER);
								conclisionTable.addCell(cell274);
								document.add(conclisionTable);

								PdfPTable conclusionConTab = new PdfPTable(pointColumnHeadLabel);
								conclusionConTab.setWidthPercentage(100); // Width 100%
								conclusionConTab.setSpacingBefore(25f); // Space before table
//				conclusionConTab.setSpacingAfter(5f); // Space after table

								PdfPCell conclusionCon1 = new PdfPCell(new Paragraph(
										"The above Risk Assessment is completely based on IS/IEC 62305-2 : 2010-12 : Risk \r\n\r\nManagement.",
										font11N));
								conclusionCon1.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclusionCon1.setBorder(PdfPCell.NO_BORDER);
								conclusionConTab.addCell(conclusionCon1);

								PdfPCell conclusionCon2 = new PdfPCell(new Paragraph(
										"\r\n\r\nDamages and losses caused due to direct or indirect strike of lightning strike are assessed.\r\n\r\n\r\n"
												+ "				As per the calculation, by providing ",
										font11N));
								conclusionCon2.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclusionCon2.setBorder(PdfPCell.NO_BORDER);
								conclusionConTab.addCell(conclusionCon2);
								classLevel = losses.getClassOfLPS();
								PdfPCell conclusionCon3 = new PdfPCell(new Paragraph(
										"\r\n\r\n"+ (
												rp1&rp2&rp3&rp4 ? losses.getClassOfLPS()+ " and "+ losses.getClassOfSPD():
													"Inadequate Protection to the structure. Increase Protection levels.")+ "\n",
										rp1&rp2&rp3&rp4 ? protectedfont11B: unProtectedfont11B));
								conclusionCon3.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclusionCon3.setBorder(PdfPCell.NO_BORDER);
								conclusionConTab.addCell(conclusionCon3);
								
								
								PdfPCell conclusionCon4 = new PdfPCell(new Paragraph(
										"\r\n\r\ncomplete risk are less than the tolerable limit and protection is achieved .",
										font11N));
								conclusionCon4.setHorizontalAlignment(Element.ALIGN_LEFT);
								conclusionCon4.setBorder(PdfPCell.NO_BORDER);
								conclusionConTab.addCell(conclusionCon4);
								document.add(conclusionConTab);

							}
						}
					}
				}
				
				recommendationPage(document,classLevel);
				
				document.close();
				writer.close();

			} catch (Exception e) {
				logger.error("Exception being thrown from PDF generation" +e.getMessage());
				throw new RiskAssessmentException("PDF Generation Failed");
			}
		} else

		{
			throw new RiskAssessmentException("Invalid Inputs");
		}

	}

	private void recommendationPage(Document document,String level) throws DocumentException, IOException {
		logger.debug("Recommendation pdf page creation started");
		document.newPage();
		Font font10B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font11N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		
		PdfPTable headingTable = createTable();
 		PdfPCell heading = new PdfPCell(new Paragraph("6. Recommendation", font12B));
 		heading.setBorder(PdfPCell.NO_BORDER);
		headingTable.addCell(heading);
 		document.add(headingTable);
 		
 		PdfPTable pdfOne = createTable();
 		PdfPCell cellOne = new PdfPCell(new Paragraph("For the protection of physical damage to structure and life hazard , Lightning Protection Sys-tem of Level "+level+" is recommended as per IEC 62305-2 ."
 				+ "", font11N));
 		cellOne.setBorder(PdfPCell.NO_BORDER);
 		pdfOne.addCell(cellOne);
 		document.add(pdfOne);
 		
 		PdfPTable pdfTwo = createTable();
 		PdfPCell cellTwo = new PdfPCell(new Paragraph("For the protection of electrical and electronic equipment in the structure , Surge protection devices are recommended as per IEC 62305-4, IEC 61643 – 11& 12 and NBC 2016."
 				+ "", font11N));
 		cellTwo.setBorder(PdfPCell.NO_BORDER);
 		pdfTwo.addCell(cellTwo);
		document.add(pdfTwo);
		
		PdfPTable pdfThree = createTable();
 		PdfPCell cellThree = new PdfPCell(new Paragraph("      For TT/TNS System", font10B));
 		cellThree.setBorder(PdfPCell.NO_BORDER);
 		pdfThree.addCell(cellThree);
		document.add(pdfThree);
		
		
		 
		 
		PdfPTable table4 = createTable();
		PdfPCell cell4 = new PdfPCell(new Paragraph("        \u2022\u00a0" +"    Protec T1HS 300 3+1 R (for Level 1 & 2)", font11N));
		cell4.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell4);
		document.add(table4);
		
		PdfPTable table4Sub = createTable();
		PdfPCell cellSub = new PdfPCell(new Paragraph("              a.  Impulse Current (10/350 "+(char) 181+"s) - 25KA L-N & 100KA N-PE", font11N));
		cellSub.setBorder(PdfPCell.NO_BORDER);
		table4Sub.addCell(cellSub);
		document.add(table4Sub);
		
		PdfPTable table5Sub = createTable();
		PdfPCell cell5Sub = new PdfPCell(new Paragraph("              b.  TOV withstand 440V for 120 mins", font11N));
		cell5Sub.setBorder(PdfPCell.NO_BORDER);
		table5Sub.addCell(cell5Sub);
		document.add(table5Sub);
		
		PdfPTable table6Sub = createTable();
		PdfPCell cell6Sub = new PdfPCell(new Paragraph("              c.  No Leakage current", font11N));
		cell6Sub.setBorder(PdfPCell.NO_BORDER);
		table6Sub.addCell(cell6Sub);
		document.add(table6Sub);
		
		
		PdfPTable tablesubHeading = createTable();
		PdfPCell cellsub1 = new PdfPCell(new Paragraph("        \u2022\u00a0" +"    Protec T1H 300 3+1 R (for Level 3 & 4)", font10B));
		cellsub1.setBorder(PdfPCell.NO_BORDER);
		tablesubHeading.addCell(cellsub1);
		document.add(tablesubHeading);
		
		PdfPTable tableSubValue1 = createTable();
		PdfPCell cellSubValue1 = new PdfPCell(new Paragraph("              a.  Impulse Current (10/350 "+(char) 181+"s) – 12.5KA L-N & 50KA N-PE", font11N));
		cellSubValue1.setBorder(PdfPCell.NO_BORDER);
		tableSubValue1.addCell(cellSubValue1);
		document.add(tableSubValue1);
		
		PdfPTable table2Sub = createTable();
		PdfPCell cell2Sub = new PdfPCell(new Paragraph("              b.  TOV withstand 440V for 120 mins", font11N));
		cell2Sub.setBorder(PdfPCell.NO_BORDER);
		table2Sub.addCell(cell2Sub);
		document.add(table2Sub);
		
		PdfPTable table3Sub = createTable();
		PdfPCell cell3Sub = new PdfPCell(new Paragraph("              c.  No Leakage current", font11N));
		cell3Sub.setBorder(PdfPCell.NO_BORDER);
		table3Sub.addCell(cell3Sub);
		document.add(table3Sub);
		
		
		PdfPTable pdfFour = createTable();
 		PdfPCell cellfour = new PdfPCell(new Paragraph("      For TNS System", font10B));
 		cellfour.setBorder(PdfPCell.NO_BORDER);
 		pdfFour.addCell(cellfour);
		document.add(pdfFour);
		
		PdfPTable tableTNSHeading = createTable();
		PdfPCell cellTNSsub1 = new PdfPCell(new Paragraph("        \u2022\u00a0" +"    Protec T1HS 300 4+0 R (for Level 1 & 2)", font10B));
		cellTNSsub1.setBorder(PdfPCell.NO_BORDER);
		tableTNSHeading.addCell(cellTNSsub1);
		document.add(tableTNSHeading);
		
		PdfPTable tableTNSValue1 = createTable();
		PdfPCell cellTNSValue1 = new PdfPCell(new Paragraph("              a. Impulse Current (10/350 "+(char) 181+"s) - 25KA per pole", font11N));
		cellTNSValue1.setBorder(PdfPCell.NO_BORDER);
		tableTNSValue1.addCell(cellTNSValue1);
		document.add(tableTNSValue1);
		
		PdfPTable tableTNSSub = createTable();
		PdfPCell cellTNSSub = new PdfPCell(new Paragraph("              b.  TOV withstand 440V for 120 mins", font11N));
		cellTNSSub.setBorder(PdfPCell.NO_BORDER);
		tableTNSSub.addCell(cellTNSSub);
		document.add(tableTNSSub);
		
		PdfPTable tableTNS3Sub = createTable();
		PdfPCell cellTNS3Sub = new PdfPCell(new Paragraph("              c.  No Leakage current", font11N));
		cellTNS3Sub.setBorder(PdfPCell.NO_BORDER);
		tableTNS3Sub.addCell(cellTNS3Sub);
		document.add(tableTNS3Sub);
		
		PdfPTable tableTNS34Heading = createTable();
		PdfPCell cellTNS34sub1 = new PdfPCell(new Paragraph("        \u2022\u00a0" +"    Protec T1H 300 4+0 R (for Level 3 & 4)", font10B));
		cellTNS34sub1.setBorder(PdfPCell.NO_BORDER);
		tableTNS34Heading.addCell(cellTNS34sub1);
		document.add(tableTNS34Heading);
		
		PdfPTable tableTNS34Value1 = createTable();
		PdfPCell cellTNS34Value1 = new PdfPCell(new Paragraph("              a. Impulse Current (10/350 "+(char) 181+"s) – 12.5KA per pole", font11N));
		cellTNS34Value1.setBorder(PdfPCell.NO_BORDER);
		tableTNS34Value1.addCell(cellTNS34Value1);
		document.add(tableTNS34Value1);
		
		PdfPTable table34TNSSub = createTable();
		PdfPCell cell34TNSSub = new PdfPCell(new Paragraph("              b.  TOV withstand 440V for 120 mins", font11N));
		cell34TNSSub.setBorder(PdfPCell.NO_BORDER);
		table34TNSSub.addCell(cell34TNSSub);
		document.add(table34TNSSub);
		
		PdfPTable table34TNS3Sub = createTable();
		PdfPCell cell34TNS3Sub = new PdfPCell(new Paragraph("              c.  No Leakage current", font11N));
		cell34TNS3Sub.setBorder(PdfPCell.NO_BORDER);
		table34TNS3Sub.addCell(cell34TNS3Sub);
		document.add(table34TNS3Sub);
		
		
		document.newPage();
		 
		//7. Legal Obligation
		PdfPTable obligationheadingTable = createTable();
 		PdfPCell obligationheading = new PdfPCell(new Paragraph("7. Legal Obligation", font12B));
 		obligationheading.setBorder(PdfPCell.NO_BORDER);
 		obligationheadingTable.addCell(obligationheading);
 		document.add(obligationheadingTable);
 		
 		PdfPTable obligation = createTable();
 		PdfPCell obligationcell = new PdfPCell(new Paragraph("The risk assessment is performed based on the inputs provided by the user / owner of the building or by persons authorised by the owner/user of the building. Procedures explained in IEC 62305-2 is followed for performing the risk assessment."
 				 , font11N));
 		obligationcell.setBorder(PdfPCell.NO_BORDER);
 		obligation.addCell(obligationcell);
 		document.add(obligation);
 		
 		PdfPTable obligation2 = createTable();
 		PdfPCell obligationcell2 = new PdfPCell(new Paragraph("Obligation to implement general safety measures as per IEC 60364 for electrical installation, in the building/structure where risk assessment is performed, lies with the user/owner of the building."
 				 , font11N));
 		obligationcell2.setBorder(PdfPCell.NO_BORDER);
 		obligation2.addCell(obligationcell2);
 		document.add(obligation2);
 		
 		PdfPTable obligation3 = createTable();
 		PdfPCell obligationcell3 = new PdfPCell(new Paragraph("Please note that all assumptions, documents, illustrations, drawings, parameters and results are not legally binding for the person performing the risk assessment."
 				+ "", font11N));
 		obligationcell3.setBorder(PdfPCell.NO_BORDER);
 		obligation3.addCell(obligationcell3);
 		document.add(obligation3);
	}
	
	private PdfPTable createTable() {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100); // Width 100%
		table.setSpacingBefore(10f); // Space before table
		
		return table;
	}

	private boolean riskProtectionForPublicServiceLoss(CalculatedRisk calculatedRisk, String lossOfHumanPublicServiceRT2) {
		String finalValue = "";
		if(calculatedRisk.getRiskProtectionR2().contains("e") || calculatedRisk.getRiskProtectionR2().contains("E")) {

			String[] r2 = calculatedRisk.getRiskProtectionR2().contains("e") ? calculatedRisk.getRiskProtectionR2().split("e"): calculatedRisk.getRiskProtectionR2().split("E");
			double conversionValue = 0;
			double convertedValue = 0;
			String firstValue = r2[0];
			String secValue = r2[1];
			char secSymbol = secValue.charAt(0);
			char secSymbolWithValue = secValue.charAt(1);
			switch(secSymbolWithValue) {
			case '1':
				conversionValue = 1;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '2':
				conversionValue = 2;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '3':
				conversionValue = 3;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '4':
				conversionValue = 4;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '5':
				conversionValue = 5;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '6':
				conversionValue = 6;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '7':
				conversionValue = 7;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '8':
				conversionValue = 8;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '9':
				conversionValue = 9;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			default:
				conversionValue = 0;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
			}
		}
		
		return (Double.valueOf(lossOfHumanPublicServiceRT2) > Double.valueOf(finalValue));
	}

	private boolean riskProtectionForCulturalHeritageLoss(CalculatedRisk calculatedRisk, String lossOfCulturalHeritageRT3) {
		String finalValue = "";
		if(calculatedRisk.getRiskProtectionR3().contains("e") || calculatedRisk.getRiskProtectionR3().contains("E")) {

			String[] r3 = calculatedRisk.getRiskProtectionR3().contains("e") ? calculatedRisk.getRiskProtectionR3().split("e"): calculatedRisk.getRiskProtectionR3().split("E");
			double conversionValue = 0;
			double convertedValue = 0;
			String firstValue = r3[0];
			String secValue = r3[1];
			char secSymbol = secValue.charAt(0);
			char secSymbolWithValue = secValue.charAt(1);
			switch(secSymbolWithValue) {
			case '1':
				conversionValue = 1;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '2':
				conversionValue = 2;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '3':
				conversionValue = 3;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '4':
				conversionValue = 4;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '5':
				conversionValue = 5;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '6':
				conversionValue = 6;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '7':
				conversionValue = 7;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '8':
				conversionValue = 8;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '9':
				conversionValue = 9;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			default:
				conversionValue = 0;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
			}
		}
		return (Double.valueOf(lossOfCulturalHeritageRT3) > Double.valueOf(finalValue));
	}

	private boolean riskProtectionForEconomicLoss(CalculatedRisk calculatedRisk, String lossOfEconomicRT4) {
		String finalValue = "";
		if(calculatedRisk.getRiskProtectionR4().contains("e") || calculatedRisk.getRiskProtectionR4().contains("E")) {

			String[] r4 = calculatedRisk.getRiskProtectionR4().contains("e") ? calculatedRisk.getRiskProtectionR4().split("e"): calculatedRisk.getRiskProtectionR4().split("E");
			double conversionValue = 0;
			double convertedValue = 0;
			String firstValue = r4[0];
			String secValue = r4[1];
			char secSymbol = secValue.charAt(0);
			char secSymbolWithValue = secValue.charAt(1);
			switch(secSymbolWithValue) {
			case '1':
				conversionValue = 1;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * ((double)secSymbol)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '2':
				conversionValue = 2;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '3':
				conversionValue = 3;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '4':
				conversionValue = 4;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '5':
				conversionValue = 5;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '6':
				conversionValue = 6;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '7':
				conversionValue = 7;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '8':
				conversionValue = 8;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '9':
				conversionValue = 9;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			default:
				conversionValue = 0;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
			}
		}
		
		return (Double.valueOf(lossOfEconomicRT4) > Double.valueOf(finalValue));
	}

	private boolean riskProtectionValuesHumanLife(CalculatedRisk calculatedRisk, String lossOfHumanLifeRT1) {
		String finalValue = "";
		if(calculatedRisk.getRiskProtectionR1().contains("e") || calculatedRisk.getRiskProtectionR1().contains("E")) {
			String[] r1 = calculatedRisk.getRiskProtectionR1().contains("e") ? calculatedRisk.getRiskProtectionR1().split("e"): calculatedRisk.getRiskProtectionR1().split("E");
			
			double conversionValue = 0;
			double convertedValue = 0;
			String firstValue = r1[0];
			String secValue = r1[1];
			char secSymbol = secValue.charAt(0);
			char secSymbolWithValue = secValue.charAt(1);
			switch(secSymbolWithValue) {
			case '1':
				conversionValue = 1;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '2':
				conversionValue = 2;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '3':
				conversionValue = 3;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '4':
				conversionValue = 4;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '5':
				conversionValue = 5;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '6':
				conversionValue = 6;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '7':
				conversionValue = 7;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '8':
				conversionValue = 8;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			case '9':
				conversionValue = 9;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
				break;
			default:
				conversionValue = 0;
				convertedValue = Double.valueOf(firstValue);
				convertedValue = convertedValue*(Math.pow(10,conversionValue * (secSymbol == '+'?+1:-1)));
				finalValue = String.valueOf(convertedValue);
			}
		}
		return (Double.valueOf(lossOfHumanLifeRT1) > Double.valueOf(finalValue));
	}

}
