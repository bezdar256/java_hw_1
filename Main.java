public class Main {
    public static void main(String[] args) {
        // Создаём матрицы из комплексных чисел размером 3x3 
        ComplexNumber[][] MatD1 = {
            { new ComplexNumber(1, 2), new ComplexNumber(3, -1), new ComplexNumber(4, 0) },
            { new ComplexNumber(0, -2), new ComplexNumber(-1, 5), new ComplexNumber(2, 3) },
            { new ComplexNumber(6, 1), new ComplexNumber(-3, -4), new ComplexNumber(0, 0) }
        };

        ComplexNumber[][] MatD2 = {
            { new ComplexNumber(2, -1), new ComplexNumber(0, 3), new ComplexNumber(-2, 2) },
            { new ComplexNumber(1, 4), new ComplexNumber(3, -3), new ComplexNumber(5, 1) },
            { new ComplexNumber(-1, 0), new ComplexNumber(2, -2), new ComplexNumber(4, 4) }
        };

        ComplexMatrix mat1 = new ComplexMatrix(MatD1);
        ComplexMatrix mat2 = new ComplexMatrix(MatD2);

        // Операции с матрицами
        System.out.println("Матрица 1:");
        System.out.println(mat1);

        System.out.println("Матрица 2:");
        System.out.println(mat2);

        // Сложение матриц
        ComplexMatrix sum = mat1.add(mat2);
        System.out.println("Сумма матриц:");
        System.out.println(sum);

        // Вычитание матриц
        ComplexMatrix subtraction = mat1.subtract(mat2);
        System.out.println("Разность матриц:");
        System.out.println(subtraction);

        // Умножение матриц
        try {
            ComplexMatrix multiplication = mat1.multiply(mat2);
            System.out.println("Произведение матриц:");
            System.out.println(multiplication);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка умножения матриц: " + e.getMessage());
        }

        // Транспонирование матрицы 1
        ComplexMatrix transpose1 = mat1.transpose();
        System.out.println("Транспонированная матрица 1:");
        System.out.println(transpose1);

        // Определитель матрицы 1
        try {
            ComplexNumber determinant1 = mat1.determinant();
            System.out.println("Определитель матрицы 1: " + determinant1);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка вычисления определителя: " + e.getMessage());
        }

        System.out.println();

        // Обратная матрица 1
        try {
            ComplexMatrix inverse1 = mat1.inverse();
            System.out.println("Обратная матрица 1:");
            System.out.println(inverse1);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления обратной матрицы: " + e.getMessage());
        }

        // Деление матриц (матрица 1 / матрица 2)
        try {
            ComplexMatrix division = mat1.divide(mat2);
            System.out.println("Результат деления матриц (матрица 1 / матрица 2):");
            System.out.println(division);
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Ошибка деления матриц: " + e.getMessage());
        }

        // Случайная матрица с целыми числами
        int size = 3;
        ComplexMatrix randomIntMatrix = new ComplexMatrix(size, size);
        randomIntMatrix.IntRandFill(10);
        System.out.println("Случайная матрица с целыми числами:");
        System.out.println(randomIntMatrix);

        // Случайная матрица с дробными числами
        ComplexMatrix randomDoubleMatrix = new ComplexMatrix(size, size);
        randomDoubleMatrix.DRandFill(10.0); 
        System.out.println("Случайная матрица с дробными числами:");
        System.out.println(randomDoubleMatrix);

        // getter и setter
        System.out.println("getter и setter:");

        System.out.println("Исходная матрица:");
        System.out.println(randomIntMatrix);

        // Получаем элемент матрицы
        ComplexNumber element = randomIntMatrix.getEntry(1, 1);
        System.out.println("Элемент (1,1): " + element);

        System.out.println();

        // Устанавливаем новое значение элемента
        randomIntMatrix.setEntry(1, 1, new ComplexNumber(123, 300));
        System.out.println("Матрица после изменения элемента (1,1):");
        System.out.println(randomIntMatrix);

        ComplexNumber newElement = randomIntMatrix.getEntry(1, 1);
        System.out.println("Новое значение элемента (1,1): " + newElement);

        System.out.println();

        System.out.println("Примеры исключений");

        System.out.println();

        //неправильное сложения матриц разных размеров
        ComplexMatrix invalidAddMatrix = null;
        try {
            invalidAddMatrix = new ComplexMatrix(2, 2);
            invalidAddMatrix.IntRandFill(5);
            ComplexMatrix sumInvalid = mat1.add(invalidAddMatrix);
            System.out.println("Сумма матриц разного размера:");
            System.out.println(sumInvalid);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при сложении матриц разного размера: " + e.getMessage());
            System.out.println("Матрица 1 (размер " + mat1.getRowCount() + "x" + mat1.getColumnCount() + "):");
            System.out.println(mat1);
            System.out.println("Матрица 2 (размер 2x2):");
            System.out.println("Сгенерированная матрица:");
            System.out.println(invalidAddMatrix);
        }

        // умножения матриц несоответствующих размеров
        ComplexMatrix invalidMultMatrix = null;
        try {
            invalidMultMatrix = new ComplexMatrix(4, 3);
            invalidMultMatrix.IntRandFill(5);
            ComplexMatrix multiplicationInvalid = mat1.multiply(invalidMultMatrix);
            System.out.println("Произведение матриц несоответствующих размеров:");
            System.out.println(multiplicationInvalid);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при умножении матриц несоответствующих размеров: " + e.getMessage());
            System.out.println("Матрица 1 (размер " + mat1.getRowCount() + "x" + mat1.getColumnCount() + "):");
            System.out.println(mat1);
            System.out.println("Матрица 2 (размер 4x3):");
            System.out.println("Сгенерированная матрица:");
            System.out.println(invalidMultMatrix);
        }

        //  вычисление обратной матрицы для вырожденной матрицы
        ComplexMatrix singularMatrix = null;
        try {
            singularMatrix = new ComplexMatrix(new ComplexNumber[][]{
                { new ComplexNumber(1, 0), new ComplexNumber(2, 0), new ComplexNumber(3, 0) },
                { new ComplexNumber(2, 0), new ComplexNumber(4, 0), new ComplexNumber(6, 0) },
                { new ComplexNumber(3, 0), new ComplexNumber(6, 0), new ComplexNumber(9, 0) }
            });
            ComplexNumber determinantSingular = singularMatrix.determinant();
            System.out.println("Определитель вырожденной матрицы: " + determinantSingular);
            ComplexMatrix inverseSingular = singularMatrix.inverse();
            System.out.println("Обратная вырожденная матрица:");
            System.out.println(inverseSingular);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления обратной матрицы для вырожденной матрицы: " + e.getMessage());
            System.out.println("Вырожденная матрица:");
            System.out.println(singularMatrix);
        }

        // деление на неквадратную матрицу
        ComplexMatrix nonSquareMatrix = null;
        try {
            nonSquareMatrix = new ComplexMatrix(3, 2);
            nonSquareMatrix.IntRandFill(5);
            ComplexMatrix divisionInvalid = mat1.divide(nonSquareMatrix);
            System.out.println("Результат деления на неквадратную матрицу:");
            System.out.println(divisionInvalid);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при делении на неквадратную матрицу: " + e.getMessage());
            System.out.println("Делимая матрица (матрица 1):");
            System.out.println(mat1);
            System.out.println("Делитель (неквадратная матрица размером 3x2):");
            System.out.println(nonSquareMatrix);
        }
    }
}
