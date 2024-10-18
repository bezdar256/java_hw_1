import java.util.Random;

public class ComplexMatrix {
    private final ComplexNumber[][] data;
    private final int numRows;
    private final int numCols;

    public ComplexMatrix(int rows, int cols) {
        this.numRows = rows;
        this.numCols = cols;
        data = new ComplexNumber[rows][cols];
        InitMatrix();
    }

    public ComplexMatrix(ComplexNumber[][] InitData) {
        this.numRows = InitData.length;
        this.numCols = InitData[0].length;
        data = new ComplexNumber[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(InitData[i], 0, data[i], 0, numCols);
        }
    }


    private void InitMatrix() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    // Геттеры
    public int getRowCount() {
        return numRows;
    }

    public int getColumnCount() {
        return numCols;
    }

    public ComplexNumber getEntry(int row, int col) {
        if (IndexCheck(row, col)) {
            return data[row][col];
        } else {
            throw new IndexOutOfBoundsException("Недопустимые индексы матрицы");
        }
    }

    // Сеттеры
    public void setEntry(int row, int col, ComplexNumber value) {
        if (IndexCheck(row, col)) {
            data[row][col] = value;
        } else {
            throw new IndexOutOfBoundsException("Недопустимые индексы матрицы");
        }
    }

    // Проверка допустимости индексов
    private boolean IndexCheck(int row, int col) {
        return (row >= 0 && row < numRows) && (col >= 0 && col < numCols);
    }

    // заполнения матрицы случайными числами
    public void DRandFill(double max) {
        Random random = new Random();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double real = random.nextDouble() * max;
                double imag = random.nextDouble() * max;
                data[i][j] = new ComplexNumber(real, imag);
            }
        }
    }

    public void IntRandFill(int max) {
        Random random = new Random();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double real = random.nextInt(max);
                double imag = random.nextInt(max);
                data[i][j] = new ComplexNumber(real, imag);
            }
        }
    }

    // Операции с матрицами
    public ComplexMatrix add(ComplexMatrix other) {
        if (numRows != other.numRows || numCols != other.numCols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают для операции сложения");
        }
        ComplexMatrix result = new ComplexMatrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.data[i][j] = data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (numRows != other.numRows || numCols != other.numCols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают для операции вычитания");
        }
        ComplexMatrix result = new ComplexMatrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.data[i][j] = data[i][j].subtract(other.data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (numCols != other.numRows) {
            throw new IllegalArgumentException("Размеры матриц не совместимы для операции умножения");
        }
        ComplexMatrix result = new ComplexMatrix(numRows, other.numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < other.numCols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < numCols; k++) {
                    sum = sum.add(data[i][k].multiply(other.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    // Вычисление определителя
    public ComplexNumber determinant() {
        if (numRows != numCols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной для вычисления определителя");
        }
        return CalcDeterminant(data);
    }

    private ComplexNumber CalcDeterminant(ComplexNumber[][] matrix) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        ComplexNumber determinant = new ComplexNumber(0, 0);
        for (int col = 0; col < size; col++) {
            ComplexNumber sign = (col % 2 == 0) ? new ComplexNumber(1, 0) : new ComplexNumber(-1, 0);
            ComplexNumber cofactor = CalcDeterminant(getSubMatrix(matrix, 0, col));
            determinant = determinant.add(sign.multiply(matrix[0][col]).multiply(cofactor));
        }
        return determinant;
    }

    private ComplexNumber[][] getSubMatrix(ComplexNumber[][] matrix, int excludeRow, int excludeCol) {
        int size = matrix.length;
        ComplexNumber[][] subMatrix = new ComplexNumber[size - 1][size - 1];
        int rowOffset = 0;
        for (int i = 0; i < size; i++) {
            if (i == excludeRow) {
                rowOffset = -1;
                continue;
            }
            int colOffset = 0;
            for (int j = 0; j < size; j++) {
                if (j == excludeCol) {
                    colOffset = -1;
                    continue;
                }
                subMatrix[i + rowOffset][j + colOffset] = matrix[i][j];
            }
        }
        return subMatrix;
    }

    // Транспонирование матрицы
    public ComplexMatrix transpose() {
        ComplexMatrix transposed = new ComplexMatrix(numCols, numRows);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                transposed.data[j][i] = data[i][j];
            }
        }
        return transposed;
    }

    // Сопряжённая матрица (присоединённая)
    public ComplexMatrix adjugate() {
        if (numRows != numCols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной для вычисления присоединенной матрицы");
        }
        ComplexMatrix adjugate = new ComplexMatrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                ComplexNumber sign = ((i + j) % 2 == 0) ? new ComplexNumber(1, 0) : new ComplexNumber(-1, 0);
                ComplexNumber minorDet = CalcDeterminant(getSubMatrix(data, i, j));
                adjugate.data[j][i] = sign.multiply(minorDet);
            }
        }
        return adjugate;
    }

    // Обратная матрица
    public ComplexMatrix inverse() {
        ComplexNumber det = determinant();
        if (det.getRP() == 0 && det.getIP() == 0) {
            throw new ArithmeticException("Матрица вырожденная и не может быть инвертирована");
        }
        ComplexMatrix adjugate = adjugate();
        return adjugate.scalarMultiply(new ComplexNumber(1, 0).divide(det));
    }

    // Умножение на скаляр
    public ComplexMatrix scalarMultiply(ComplexNumber scalar) {
        ComplexMatrix result = new ComplexMatrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.data[i][j] = data[i][j].multiply(scalar);
            }
        }
        return result;
    }

    // Деление матриц (умножение на обратную)
    public ComplexMatrix divide(ComplexMatrix other) {
        if (other.numRows != other.numCols) {
            throw new IllegalArgumentException("Нельзя делить на неквадратную матрицу");
        }
        return this.multiply(other.inverse());
    }

  
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int maxLength = MaxElem();
        for (int i = 0; i < numRows; i++) {
            builder.append("[");
            for (int j = 0; j < numCols; j++) {
                String elementStr = data[i][j].toString();
                builder.append(String.format("%" + maxLength + "s", elementStr));
                if (j != numCols - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]\n");
        }
        return builder.toString();
    }

    private int MaxElem() {
        int maxLen = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int len = data[i][j].toString().length();
                if (len > maxLen) {
                    maxLen = len;
                }
            }
        }
        return maxLen;
    }
}
