package rational;

/**
 * Provides rational number (fraction) objects. The rational arithmetic provided by Rational objects is subject to
 * integer overflow if the numerator and/or denominator becomes too large.
 */
public class Rat
{
    /**
     * The numerator of this Rat. The gcd of |num| and den is always 1.
     */
    private long num;

    /**
     * The denominator of this Rat. den must be positive.
     */
    private long den;

    /**
     * Creates the rational number 0
     */
    public Rat ()
    {
        num = 0;
        den = 1;
    }

    /**
     * Creates the rational number n
     */
    public Rat (long n)
    {
        num = n;
        den = 1;
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public Rat (long n, long d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException();
        }

        // Deals with signs
        if (d < 0)
        {
            d = -d;
            n = -n;
        }

        // Deal with lowest terms
        long g = gcd(Math.abs(n), d);
        num = n / g;
        den = d / g;
    }

    /**
     * Returns the sum of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.add(y); a/b + c/d = (ad +
     * bc) / bd
     */
    public Rat add (Rat r)
    {
        long n = this.num * r.den + this.den * r.num;
        long d = this.den * r.den;
        return new Rat(n, d);
    }

    /**
     * Returns the difference of this and r a/b - c/d = (ad - bc) / bd
     */
    public Rat sub (Rat r)
    {
        long n = this.num * r.den - this.den * r.num;
        long d = this.den * r.den;
        return new Rat(n, d);
    }

    /**
     * Returns the product of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.mul(y); a/b * c/d =
     * ac/bd
     */
    public Rat mul (Rat r)
    {
        return new Rat(this.num * r.num, this.den * r.den);
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the quotient of this and r. a/b / c/d = ad /
     * bc
     */
    public Rat div (Rat r)
    {
        if (r.num == 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            return new Rat(this.num * r.den, this.den * r.num);
        }
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive number if this > r To compare a/b and c/d,
     * compare ad and bc
     */
    public int compareTo (Rat r)
    {
        long diff = this.num * r.den - this.den * r.num;
        if (diff < 0)
        {
            return -1;
        }
        else if (diff > 0)
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
        if (den == 1)
        {
            return num + "";
        }
        else
        {
            return num + "/" + den;
        }
    }

    /**
     * Converts this to the closest double
     */
    public double toDouble ()
    {
        return num / (double) den;
    }

    /**
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     */
    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
