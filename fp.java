/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floatingpoint;

/*
 *
 * @author milus
 */
public class fp
{
    public String myName() {
        return "Milush Yanev";
    }

    public int add(int a, int b) {

        FPNumber fa = new FPNumber(a);
        FPNumber fb = new FPNumber(b);
        float aFloat = Float.intBitsToFloat(a);
        float bFloat = Float.intBitsToFloat(b);

        // handle exceptions
        if(fa.isNaN()) return a; //NaN
        if(fb.isNaN()) return b; //NaN
        if(fa.isZero()) return b;
        if(fb.isZero()) return a;
        if(Float.isInfinite(aFloat) && Float.isInfinite(bFloat))
            if (fa._s == fb._s)
                return (fa._s == 1) ? 0x7F800000 : 0xFF800000; //+infinity or -infinity
             else return 0x7F800001; //NaN
        if(Float.isInfinite(aFloat) && !Float.isInfinite(bFloat)) return a;
        if(!Float.isInfinite(aFloat) && Float.isInfinite(bFloat)) return b;

        // sort numbers so larger significand is A
        if(Math.abs(aFloat) >= Math.abs(bFloat)) {
             fa = new FPNumber(a);
             fb = new FPNumber(b);
        }
        else {
             fa = new FPNumber(b);
             fb = new FPNumber(a);
        }

        // align mantissa
        int shift = fa._e - fb._e;
        if(shift > 24)
            return (fa._e == 1) ? 0 : 0x80000000;
        fb.setF(fb._f >> shift);

        // ADD or SUB
        long r_f;
        if(fa._s == fb._s)
            r_f =fa.f() + fb.f();
        else {
            r_f =fa.f() - fb.f();
            if(r_f == 0)
                return (fa._s == 1) ? 0 : 0x80000000;
        }

        // combine components
        FPNumber result = new FPNumber(0);
        result.setS(fa._s);
        result.setE(fa._e);
        result.setF(r_f);
        return result.asInt();
    }

    public int mul(int a, int b) {
        FPNumber fa = new FPNumber(a);
        FPNumber fb = new FPNumber(b);
        float aFloat = Float.intBitsToFloat(a);
        float bFloat = Float.intBitsToFloat(b);
        int r_s = fa._s == fb._s ? 1 : -1;

        // handle exceptions
        if(Float.isNaN(aFloat)) return a; //NaN
        if(Float.isNaN(bFloat)) return b; //NaN
        if(Float.isInfinite(aFloat) && (b == 0 || b == 0x80000000))
            return 0x7F800001; // NaN
        if(Float.isInfinite(bFloat) && (a == 0 || a == 0x80000000))
            return 0x7F800001; // NaN
        if (a == 0 || a == 0x80000000 || b == 0 || b == 0x80000000)
            return fa._s == fb._s ? 0 : 0x80000000; // +0.0 or -0.0
        if(Float.isInfinite(aFloat) || Float.isInfinite(bFloat))
            return r_s == 1 ? 0x7F800000 : 0xFF800000; // +infinity or -infinity

        // add exponents and subtract bias
        int exponent = fa._e + fb._e - 127;
        if(exponent > 254) return r_s == 1 ? 0x7F800000 : 0xFF800000;
        if(exponent < 0) return r_s == 1 ? 0 : 0x80000000;

        String aMBits = Long.toBinaryString(fa._f);
        String bMBits = Long.toBinaryString(fb._f);
        //String MProd = "";
        int  bitB;

        long r_f = fa.f() * fb.f();
        r_f = r_f >> 25;

        // combine components
        FPNumber product = new FPNumber(0);
        product.setS(r_s);
        product.setE(exponent);
        product.setF(r_f);

        // normalize
        if(Float.intBitsToFloat(a) * Float.intBitsToFloat(b) != Float.intBitsToFloat(product.asInt())) {
            product.setE(product._e + 1);
            product.setF(r_f >> 1);

        }
        return product.asInt();

    }

public static void main(String[] args)
	{   
		int v24_25	= 0x41C20000; // 24.25
		int v_1875	= 0xBE400000; // -0.1875
		int v5		= 0xC0A00000; // -5.0
                
        fp m = new fp();
        FPNumber fa = new FPNumber(v24_25);
        FPNumber fb = new FPNumber(v5);
       
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v_1875)) + " should be 24.0625");
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v5)) + " should be 19.25");
		System.out.println(Float.intBitsToFloat(m.add(v_1875, v5)) + " should be -5.1875");

		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v_1875)) + " should be -4.546875");
		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v5)) + " should be -121.25");
		System.out.println(Float.intBitsToFloat(m.mul(v_1875, v5)) + " should be 0.9375");
                        
	}
}
