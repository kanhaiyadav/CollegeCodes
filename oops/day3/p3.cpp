/*Question 3: Create a class Matrix with two data members row and column.
Use dynamic memory allocation for two dimension array and
then perform matrix multiplication using operator overloading.*/

#include <iostream>
using namespace std;

class Matrix
{
private:
    int row, col;
    int **arr;

public:
    Matrix(int r, int c) : row(r), col(c)
    {
        arr = new int *[row];
        for (int i = 0; i < row; i++)
        {
            arr[i] = new int[col]();
        }
    }

    ~Matrix()
    {
        for (int i = 0; i < row; i++)
        {
            delete[] arr[i];
        }
        delete[] arr;
    }

    void setValues()
    {
        cout << "Enter elements of " << row << "x" << col << " matrix:\n";
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                cin >> arr[i][j];
            }
        }
    }

    void display() const
    {
        cout << "Matrix:\n";
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                cout << arr[i][j] << " ";
            }
            cout << endl;
        }
    }

    Matrix operator*(const Matrix &other)
    {
        if (col != other.row)
        {
            cout << "\n\n!!Matrix dimensions do not allow multiplication\n\n"
                 << endl;
            exit(1);
        }

        Matrix result(row, other.col);

        for (int i = 0; i < row; ++i)
        {
            for (int j = 0; j < other.col; ++j)
            {
                result.arr[i][j] = 0;
                for (int k = 0; k < col; ++k)
                {
                    result.arr[i][j] += arr[i][k] * other.arr[k][j];
                }
            }
        }
        return result;
    }
};

int main()
{
    int r1, c1, r2, c2;

    cout << "Enter rows and columns for first matrix: ";
    cin >> r1 >> c1;
    Matrix mat1(r1, c1);
    mat1.setValues();

    cout << "Enter rows and columns for second matrix: ";
    cin >> r2 >> c2;
    Matrix mat2(r2, c2);
    mat2.setValues();

    cout << "First ";
    mat1.display();

    cout << "Second ";
    mat2.display();

    Matrix result = mat1 * mat2;

    if (r1 > 0 && c2 > 0)
    {
        cout << "Result ";
        result.display();
    }

    return 0;
}
