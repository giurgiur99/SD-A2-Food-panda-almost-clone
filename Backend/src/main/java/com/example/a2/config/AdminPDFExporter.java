package com.example.a2.config;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.a2.model.Food;
import com.example.a2.model.Menu;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class AdminPDFExporter {
    private List<Menu> listMenu;

    public AdminPDFExporter(List<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Menu name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Food name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        for (Menu menu : listMenu) {
            List<Food> listFood = menu.getFoods();
            for (Food food : listFood) {
                table.addCell(String.valueOf(menu.getName()));
                table.addCell(food.getName());
                table.addCell(String.valueOf(food.getPrice()));
                table.addCell(food.getDescription());
            }
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Menu list", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
