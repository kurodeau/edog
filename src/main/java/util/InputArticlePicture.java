package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputArticlePicture {

    private static final String TABLE_NAME = "articlePic";
    private static final String BLOB_COLUMN_NAME = "articlePicBlob";

    public static void main(String[] args) {
        String imagePath = "C:\\Users\\EX14\\Pictures\\Acer\\A.jpg";

        try {
            byte[] imageData = readImage(imagePath);
            int rowCount = getRowCount(TABLE_NAME);
            updateDatabase(imageData, TABLE_NAME, BLOB_COLUMN_NAME, rowCount);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readImage(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

    private static int getRowCount(String tableName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD)) {
            String sql = "SELECT COUNT(*) FROM edog." + tableName;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    private static void updateDatabase(byte[] imageData, String tableName, String blobColumnName, int rowCount) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD)) {
            String sql = "UPDATE edog." + tableName + " SET " + blobColumnName + " = ? WHERE 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBytes(1, imageData);
                int updatedRows = statement.executeUpdate();
                System.out.println("Updated " + updatedRows + " rows with the same image.");
            }
        }
    }
}
