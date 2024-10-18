public class ComplexNumber {
    private double realPart;
    private double imagPart;

    public ComplexNumber(double realPart, double imagPart) {
        this.realPart = realPart;
        this.imagPart = imagPart;
    }

    // Геттеры
    public double getRP() {
        return realPart;
    }

    public double getIP() {
        return imagPart;
    }

    // Сеттеры
    public void setRP(double realPart) {
        this.realPart = realPart;
    }

    public void setIP(double imagPart) {
        this.imagPart = imagPart;
    }

    // Арифметические операции
    public ComplexNumber add(ComplexNumber other) {
        double newReal = this.realPart + other.realPart;
        double newImag = this.imagPart + other.imagPart;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        double newReal = this.realPart - other.realPart;
        double newImag = this.imagPart - other.imagPart;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.realPart * other.realPart - this.imagPart * other.imagPart;
        double newImag = this.realPart * other.imagPart + this.imagPart * other.realPart;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other.realPart == 0 && other.imagPart == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        double denominator = other.realPart * other.realPart + other.imagPart * other.imagPart;
        double newReal = (this.realPart * other.realPart + this.imagPart * other.imagPart) / denominator;
        double newImag = (this.imagPart * other.realPart - this.realPart * other.imagPart) / denominator;
        return new ComplexNumber(newReal, newImag);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof ComplexNumber) {
            ComplexNumber other = (ComplexNumber) obj;
            return (this.realPart == other.realPart) && (this.imagPart == other.imagPart);
        }
        return false;
    }


    @Override
    public String toString() {
        String realStr;
        String imagStr;

        if (realPart % 1 == 0) {
            realStr = String.format("%.0f", realPart);
        } else {
            realStr = String.format("%.3f", realPart);
        }

        if (imagPart % 1 == 0) {
            imagStr = String.format("%.0f", Math.abs(imagPart));
        } else {
            imagStr = String.format("%.3f", Math.abs(imagPart));
        }

        String sign = imagPart >= 0 ? "+" : "-";
        return String.format("%s %s %si", realStr, sign, imagStr);
    }
}
