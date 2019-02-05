package rational;

import java.math.BigInteger;

/**
 * Provides rational number (fraction) objects. The rational arithmetic provided by Rational objects is subject to
 * integer overflow if the numerator and/or denominator becomes too large.
 */
public class BigRat
{
    /**
     * public variable static variable that allows a user to access a BigInteger with a value of 1
     */
    public static final BigInteger ONE = new BigInteger("1");

    /**
     * public variable static variable that allows a user to access a BigInteger with a value of 0
     */
    public static final BigInteger ZERO = new BigInteger("0");

    /**
     * The numerator of this Rat. The gcd of |num| and den is always 1.
     */
    private BigInteger num;

    /**
     * The denominator of this Rat. den must be positive.
     */
    private BigInteger den;

    /**
     * Creates the rational number 0
     */
    public BigRat ()
    {
        num = ZERO;
        den = ONE;
    }

    /**
     * Creates the rational number n
     */
    public BigRat (long n)
    {
        num = new BigInteger(n + "");
        den = ONE;
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public BigRat (long n, long d)
    {
        BigInteger bigIntD = new BigInteger(d + "");
        BigInteger bigIntN = new BigInteger(n + "");
        if (bigIntD.equals(ZERO))
        {
            throw new IllegalArgumentException();
        }

        // Deals with signs
        if (bigIntD.compareTo(ZERO) == -1)
        {
            bigIntD = bigIntD.multiply(BigInteger.valueOf(-1));
            bigIntN = bigIntN.multiply(BigInteger.valueOf(-1));
        }

        // Deal with lowest terms
        BigInteger g = bigIntN.abs().gcd(bigIntD.abs());
        num = bigIntN.divide(g);
        den = bigIntD.divide(g);
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */

    public BigRat (BigInteger n, BigInteger d)
    {
        if (d.equals(ZERO))
        {
            throw new IllegalArgumentException();
        }

        // Deals with signs
        if (d.compareTo(ZERO) == -1)
        {
            d = d.multiply(BigInteger.valueOf(-1));
            n = n.multiply(BigInteger.valueOf(-1));
        }

        // Deal with lowest terms
        BigInteger g = n.abs().gcd(d.abs());
        num = n.divide(g);
        den = d.divide(g);
    }

    /**
     * Returns the sum of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.add(y); a/b + c/d = (ad +
     * bc) / bd
     */
    public BigRat add (BigRat r)
    {
        BigInteger n = this.num.multiply(r.den).add(this.den.multiply(r.num));
        BigInteger d = this.den.multiply(r.den);
        return new BigRat(n, d);
    }

    /**
     * Returns the difference of this and r a/b - c/d = (ad - bc) / bd
     */
    public BigRat sub (BigRat r)
    {
        BigInteger n = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
        BigInteger d = this.den.multiply(r.den);
        return new BigRat(n, d);
    }

    /**
     * Returns the product of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.mul(y); a/b * c/d =
     * ac/bd
     */
    public BigRat mul (BigRat r)
    {
        return new BigRat(this.num.multiply(r.num), this.den.multiply(r.den));
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the quotient of this and r. a/b / c/d = ad /
     * bc
     */
    public BigRat div (BigRat r)
    {
        if (r.num.equals(ZERO))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            return new BigRat(this.num.multiply(r.den), this.den.multiply(r.num));
        }
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive number if this > r To compare a/b and c/d,
     * compare ad and bc
     */
    public int compareTo (BigRat r)
    {
        BigInteger diff = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
        if (diff.compareTo(ZERO) == -1)
        {
            return -1;
        }
        else if (diff.compareTo(ZERO) == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns a string version of this in simplest and lowest terms. Examples: 3/4 => "3/4" 6/8 => "3/4" 2/1 => "2" 0/8
     * => "0" 3/-4 => "-3/4"
     */
    public String toString ()
    {
        if (den.equals(ONE))
        {
            return num + "";
        }
        else
        {
            return num + "/" + den;
        }
    }
}
