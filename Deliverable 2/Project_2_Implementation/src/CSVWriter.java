import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    private String filePath;
    private final String[] header = {"Email", "Password", "Type"};
    private final String[] header1 = {"Item Type", "Item ID", "Title", "Location", "Available"};


    public CSVWriter(String filepath) {
        this.filePath = filepath;
    }

    public void writeData(List<String[]> rows) throws IOException {
        boolean writeHeader = false;
        // Check if file exists
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            writeHeader = true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write header if necessary
            if (writeHeader) {
                writeRow(writer, header);
                writer.newLine();
                
            }
            
            // Write data rows
            for (String[] rowData : rows) {
                writeRow(writer, rowData);
            }
        }
    }
    public void writeData1(List<List<String[]>> sections) throws IOException {
        boolean writeHeader = false;
        // Check if file exists
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            writeHeader = true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write header if necessary
            if (writeHeader) {
                writeRow(writer, header1);
                writer.newLine();
            }

            // Write data for each section
            for (List<String[]> section : sections) {
                writeSection(writer, section);
            }
        }
    }

    private void writeRow(BufferedWriter writer, String[] rowData) throws IOException {
        StringBuilder rowBuilder = new StringBuilder();
        for (String data : rowData) {
            rowBuilder.append(data).append(",");
        }
        String row = rowBuilder.deleteCharAt(rowBuilder.length() - 1).toString();
        writer.write(row);
        writer.newLine();
    }

    private void writeSection(BufferedWriter writer, List<String[]> section) throws IOException {
        for (String[] rowData : section) {
            writeRow(writer, rowData);
        }
    }
}
