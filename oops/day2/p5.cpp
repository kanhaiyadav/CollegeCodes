#include <iostream>
using namespace std;

class Matrix
{
private:
    int rows, cols;
    int **data;

public:
    Matrix(int r = 0, int c = 0) : rows(r), cols(c)
    {
        data = new int *[rows];
        for (int i = 0; i < rows; ++i)
        {
            data[i] = new int[cols];
        }
    }

    ~Matrix()
    {
        for (int i = 0; i < rows; ++i)
        {
            delete[] data[i];
        }
        delete[] data;
    }

    void inputMatrix()
    {
        cout << "Enter elements of the matrix (" << rows << "x" << cols << "):\n";
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < cols; ++j)
            {
                cin >> data[i][j];
            }
        }
    }

    void displayMatrix() const
    {
        cout << "Matrix (" << rows << "x" << cols << "):\n";
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < cols; ++j)
            {
                cout << data[i][j] << " ";
            }
            cout << endl;
        }
    }

    Matrix operator*(const Matrix &other)
    {
        if (cols != other.rows)
        {
            cout << "\n\n!!Matrix dimensions do not allow multiplication\n\n"
                 << endl;
            exit(1);
        }

        Matrix result(rows, other.cols);

        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < other.cols; ++j)
            {
                result.data[i][j] = 0;
                for (int k = 0; k < cols; ++k)
                {
                    result.data[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }
};

int main()
{
    int rows1, cols1, rows2, cols2;

    cout << "Enter the number of rows and columns for the first matrix: ";
    cin >> rows1 >> cols1;

    cout << "Enter the number of rows and columns for the second matrix: ";
    cin >> rows2 >> cols2;

    if (cols1 != rows2)
    {
        cout << "!!Matrix multiplication is not possible with these dimensions" << endl;
        return 1;
    }

    Matrix mat1(rows1, cols1);
    Matrix mat2(rows2, cols2);

    mat1.inputMatrix();
    mat2.inputMatrix();

    cout << "\nFirst ";
    mat1.displayMatrix();
    cout << "\nSecond ";
    mat2.displayMatrix();

    Matrix result = mat1 * mat2;

    cout << "\nResult of matrix multiplication:\n";
    result.displayMatrix();

    return 0;
}
