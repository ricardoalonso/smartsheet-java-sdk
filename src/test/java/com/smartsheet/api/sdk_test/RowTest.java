package com.smartsheet.api.sdk_test;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2023 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.CellLink;
import com.smartsheet.api.models.Duration;
import com.smartsheet.api.models.Hyperlink;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Predecessor;
import com.smartsheet.api.models.PredecessorList;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {


	@Test
	void ListSheets_NoParams()
	{
		try {
			Smartsheet ss = HelperFunctions.SetupClient("List Sheets - No Params");
			PagedResult<Sheet> sheets = ss.sheetResources().listSheets(null, null, null);
			assertThat(sheets.getData().get(0).getName()).isEqualTo("Copy of Sample Sheet");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_String()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - String");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue("Banana");
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Yellow Fruit");
			rowB.setCells(Arrays.asList(cell3, cell4));


			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1L, Arrays.asList(rowA,rowB));

			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_Location_Top()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Location - Top");

			Row rowA = new Row();
			rowA.setToTop(true);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(0).getValue()).isEqualTo("Apple");
			assertThat(addedRows.get(0).getRowNumber().intValue()).isEqualTo(1);
			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}

	}

  	@Test
	void addRows_Location_Bottom()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Location - Bottom");

			Row rowA = new Row();
			rowA.setToBottom(true);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(0).getValue()).isEqualTo("Apple");
			assertThat(addedRows.get(0).getRowNumber().intValue()).isEqualTo(100);
			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_Int()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Int");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(100);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("One Hundred");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(2.1);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Two Point One");
			rowB.setCells(Arrays.asList(cell3, cell4));


			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA, rowB));

			assertThat(addedRows.get(0).getCells().get(0).getValue()).isEqualTo(100);
			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_Bool()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Bool");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(true);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("This is True");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(false);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("This is False");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA, rowB));

			assertThat(addedRows.get(0).getId().intValue()).isEqualTo(10);
			assertThat(addedRows.get(0).getCells().get(0).getValue()).isEqualTo(true);
			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignFormulae()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Formulae");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(1).getFormula()).isEqualTo("=SUM([Column2]3, [Column2]3, [Column2]4)");
			assertThat(addedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(0).getValue()).isEqualTo("Google");
			assertThat(addedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(addedRows.get(0).getCells().get(0).getHyperlink().getUrl()).isEqualTo("http://google.com");

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_HyperlinkSheetID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink SheetID");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Sheet2");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Sheet3");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setSheetId(3L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(1).getValue()).isEqualTo("Sheet3");
			assertThat(addedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);
			assertThat(addedRows.get(0).getCells().get(1).getHyperlink().getSheetId().longValue()).isEqualTo(3L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_AssignValues_HyperlinkReportID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink ReportID");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Report9");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setReportId(9L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Report8");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setReportId(8L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(1).getValue()).isEqualTo("Report8");
			assertThat(addedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);
			assertThat(addedRows.get(0).getCells().get(1).getHyperlink().getReportId().longValue()).isEqualTo(8L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_Invalid_AssignHyperlinkUrlandSheetId()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Invalid - Assign Hyperlink URL and SheetId");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			assertThat(ex.getMessage()).isEqualTo("hyperlink.url must be null for sheet, report, or Sight hyperlinks.");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void addRows_Invalid_AssignValueAndFormulae()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Invalid - Assign Value and Formulae");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			cell1.setValue("20");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			assertThat(ex.getMessage()).isEqualTo("If cell.formula is specified, then value, objectValue, image, hyperlink, and linkInFromCell must not be specified.");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void AddRows_AssignObjectValue_PredecessorList()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Object Value - Predecessor List (using floats)");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			Duration duration = new Duration();
			duration.setDays(2.5);
			Predecessor predecessor = new Predecessor();
			predecessor.setRowId(10L);
			predecessor.setType("FS");
			predecessor.setLag(duration);
			PredecessorList predecessorList = new PredecessorList();
			predecessorList.setPredecessors(Arrays.asList(predecessor));
			cell1.setObjectValue(predecessorList);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			assertThat(addedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(101L);
			assertThat(addedRows.get(0).getCells().get(1).getValue()).isEqualTo("2FS +2.5d");

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_String()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - String");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue("Banana");
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Yellow Fruit");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isEqualTo("Apple");

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_Int()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Int");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(100);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("One Hundred");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(2.1);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Two Point One");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isEqualTo(100);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_Bool()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Bool");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(true);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("This is True");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(false);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("This is False");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isEqualTo(true);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignFormulae()
	{
		try{
			Smartsheet ss =  HelperFunctions.SetupClient("Update Rows - Assign Formulae");

			Row rowA = new Row();
			rowA.setId(11L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(1).getFormula()).isEqualTo("=SUM([Column2]3, [Column2]3, [Column2]4)");
			assertThat(updatedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isEqualTo("Google");
			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getHyperlink().getUrl()).isEqualTo("http://google.com");

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_HyperlinkSheetID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink SheetID");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Sheet2");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Sheet3");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setSheetId(3L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(1).getValue()).isEqualTo("Sheet3");
			assertThat(updatedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);
			assertThat(updatedRows.get(0).getCells().get(1).getHyperlink().getSheetId().longValue()).isEqualTo(3L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_AssignValues_HyperlinkReportID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink ReportID");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Report9");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setReportId(9L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Report8");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setReportId(8L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(1).getValue()).isEqualTo("Report8");
			assertThat(updatedRows.get(0).getCells().get(1).getColumnId().longValue()).isEqualTo(102L);
			assertThat(updatedRows.get(0).getCells().get(1).getHyperlink().getReportId().longValue()).isEqualTo(8L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_Invalid_AssignHyperlinkUrlandSheetId()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Invalid - Assign Hyperlink URL and SheetId");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			assertThat(ex.getMessage()).isEqualTo("hyperlink.url must be null for sheet, report, or Sight hyperlinks.");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_Invalid_AssignValueAndFormulae()
	{
		try{
			Smartsheet ss =  HelperFunctions.SetupClient("Update Rows - Invalid - Assign Value and Formulae");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			cell1.setValue("20");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			assertThat(ex.getMessage()).isEqualTo("If cell.formula is specified, then value, objectValue, image, hyperlink, and linkInFromCell must not be specified.");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_ClearValue_TextNumber()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Text Number");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isNull();

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	void UpdateRows_ClearValue_Checkbox()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Checkbox");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isEqualTo(false);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	void UpdateRows_ClearValue_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Hyperlink");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			cell1.setHyperlink(new Hyperlink());
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isNull();
			assertThat(updatedRows.get(0).getCells().get(0).getHyperlink()).isNull();

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	void UpdateRows_ClearValue_CellLink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Cell Link");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			cell1.setLinkInFromCell(new CellLink());
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getCells().get(0).getColumnId().longValue()).isEqualTo(101L);
			assertThat(updatedRows.get(0).getCells().get(0).getValue()).isNull();
			assertThat(updatedRows.get(0).getCells().get(0).getLinkInFromCell()).isNull();

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	void UpdateRows_Invalid_AssignHyperlinkAndCellLink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Invalid - Assign Hyperlink and Cell Link");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			Hyperlink hyperlink = new Hyperlink();
			hyperlink.setUrl("www.google.com");
			cell1.setHyperlink(hyperlink);
			CellLink cellLink = new CellLink();
			cellLink.setRowId(20L);
			cellLink.setSheetId(2L);
			cellLink.setColumnId(201L);
			cell1.setLinkInFromCell(cellLink);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			assertThat(ex.getMessage()).isEqualTo("Only one of cell.hyperlink or cell.linkInFromCell may be non-null.");
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	void UpdateRows_Location_Top()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Location - Top");

			Row rowA = new Row();
			rowA.setId(10L);
			rowA.setToTop(true);

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getId().longValue()).isEqualTo(10L);
			assertThat(updatedRows.get(0).getRowNumber().longValue()).isEqualTo(1L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	void UpdateRows_Location_Bottom()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Location - Bottom");

			Row rowA = new Row();
			rowA.setId(10L);
			rowA.setToBottom(true);

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			assertThat(updatedRows.get(0).getId().longValue()).isEqualTo(10L);
			assertThat(updatedRows.get(0).getRowNumber().longValue()).isEqualTo(100L);

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}
}
