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
	public String myName()
	{
		return "Milush Yanev";
	}

	public int add(int a, int b)
	{
                
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);
                
                if(Float.isNaN(a)) return a;
                if(Float.isNaN(b)) return b;
                if(a==0 || a==0x80000000) return b;
                if(b==0 || b==0x80000000) return a;
                if(Float.isInfinite(a) && Float.isInfinite(b))
                    if(fa._s == fb._s)
                        return (fa._s == 1) ? 0x7F800000 : 0xFF80000;
                String str2=Integer.toBinaryString((int)fa._f);
                String str3=Integer.toBinaryString((int)fa._e);
                String str1=Integer.toBinaryString((int)fb._f);
                String str4=Integer.toBinaryString((int)fb._e);

                 String str = "0"+str3+str2.charAt(1)+str2.charAt(2)+str2.charAt(3)+str2.charAt(4)
                         +str2.charAt(5)+str2.charAt(6)+str2.charAt(7)+str2.charAt(8)+str2.charAt(9)
                         +str2.charAt(10)+str2.charAt(11)+str2.charAt(12)+str2.charAt(13)+str2.charAt(14)
                         +str2.charAt(15)+str2.charAt(16)+str2.charAt(17)+str2.charAt(18)+str2.charAt(19)
                         +str2.charAt(20)+str2.charAt(21)+str2.charAt(22)+str2.charAt(23);
                 String str5 = "0"+str4+str1.charAt(1)+str1.charAt(2)+str1.charAt(3)+str1.charAt(4)
                         +str1.charAt(5)+str1.charAt(6)+str1.charAt(7)+str1.charAt(8)+str1.charAt(9)
                         +str1.charAt(10)+str1.charAt(11)+str1.charAt(12)+str1.charAt(13)+str1.charAt(14)
                         +str1.charAt(15)+str1.charAt(16)+str1.charAt(17)+str1.charAt(18)+str1.charAt(19)
                         +str1.charAt(20)+str1.charAt(21)+str1.charAt(22)+str1.charAt(23);

            float faa = GetFloat32( str );   
            float fbb = GetFloat32( str5 );  
            float result1=((fa._s)*faa)+((fb._s)*fbb);
            
            if (result1>0){
            
            String str12="0"+GetBinary32(result1);
            result._s=1;
            String posE=""+str12.charAt(1)+str12.charAt(2)+str12.charAt(3)+str12.charAt(4)+str12.charAt(5)+str12.charAt(6)
                    +str12.charAt(7)+str12.charAt(8);
            int val=Integer.parseInt(posE, 2);
            //System.out.println(val);
            result._e=val;
            String posM="1"+str12.charAt(9)+str12.charAt(10)+str12.charAt(11)+str12.charAt(12)+str12.charAt(13)+str12.charAt(14)
                    +str12.charAt(15)+str12.charAt(16)+str12.charAt(17)+str12.charAt(18)+str12.charAt(19)+str12.charAt(20)
                    +str12.charAt(21)+str12.charAt(22)+str12.charAt(23)+str12.charAt(24)+str12.charAt(25)+str12.charAt(26)
                    +str12.charAt(27)+str12.charAt(28)+str12.charAt(29)+str12.charAt(30)+str12.charAt(31)+"00";
            int val1=Integer.parseInt(posM, 2);
            result._f=val1;
         
            }   
            else{
            String str12=""+GetBinary32(result1);
            result._s=-1;
            String posE=""+str12.charAt(1)+str12.charAt(2)+str12.charAt(3)+str12.charAt(4)+str12.charAt(5)+str12.charAt(6)
                    +str12.charAt(7)+str12.charAt(8);
            int val=Integer.parseInt(posE, 2);
            //System.out.println(val);
            result._e=val;
            String posM="1"+str12.charAt(9)+str12.charAt(10)+str12.charAt(11)+str12.charAt(12)+str12.charAt(13)+str12.charAt(14)
                    +str12.charAt(15)+str12.charAt(16)+str12.charAt(17)+str12.charAt(18)+str12.charAt(19)+str12.charAt(20)
                    +str12.charAt(21)+str12.charAt(22)+str12.charAt(23)+str12.charAt(24)+str12.charAt(25)+str12.charAt(26)
                    +str12.charAt(27)+str12.charAt(28)+str12.charAt(29)+str12.charAt(30)+str12.charAt(31)+"00";
            int val1=Integer.parseInt(posM, 2);
            result._f=val1;
            //System.out.println(val1);
            
            }
		return result.asInt();
	}
        
	public int mul(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);
                
                if(Float.isNaN(a)) return a;
                if(Float.isNaN(b)) return b;
                if(a==0 && Float.isInfinite(b)) return a;
                 if(b==0 && Float.isInfinite(a)) return b;    
                if(a==0 || a==0x80000000) return result.asInt();
                if(b==0 || b==0x80000000) return result.asInt();
                if(Float.isInfinite(a) && Float.isInfinite(b))
                    if(fa._s == fb._s)
                        return (fa._s == 1) ? 0x7F800000 : 0xFF80000;
                if(Float.isInfinite(a) || Float.isInfinite(b))
                    if(fa._s == fb._s)
                        return (fa._s == 1) ? 0x7F800000 : 0xFF80000;
                        
                
                String str2=Integer.toBinaryString((int)fa._f);
                String str3=Integer.toBinaryString((int)fa._e);
                String str1=Integer.toBinaryString((int)fb._f);
                String str4=Integer.toBinaryString((int)fb._e);

                 String str = "0"+str3+str2.charAt(1)+str2.charAt(2)+str2.charAt(3)+str2.charAt(4)
                         +str2.charAt(5)+str2.charAt(6)+str2.charAt(7)+str2.charAt(8)+str2.charAt(9)
                         +str2.charAt(10)+str2.charAt(11)+str2.charAt(12)+str2.charAt(13)+str2.charAt(14)
                         +str2.charAt(15)+str2.charAt(16)+str2.charAt(17)+str2.charAt(18)+str2.charAt(19)
                         +str2.charAt(20)+str2.charAt(21)+str2.charAt(22)+str2.charAt(23);
                 String str5 = "0"+str4+str1.charAt(1)+str1.charAt(2)+str1.charAt(3)+str1.charAt(4)
                         +str1.charAt(5)+str1.charAt(6)+str1.charAt(7)+str1.charAt(8)+str1.charAt(9)
                         +str1.charAt(10)+str1.charAt(11)+str1.charAt(12)+str1.charAt(13)+str1.charAt(14)
                         +str1.charAt(15)+str1.charAt(16)+str1.charAt(17)+str1.charAt(18)+str1.charAt(19)
                         +str1.charAt(20)+str1.charAt(21)+str1.charAt(22)+str1.charAt(23);

            float faa = GetFloat32( str );   
            float fbb = GetFloat32( str5 );  
            float result1=((fa._s)*faa)*((fb._s)*fbb);
            
            if (result1>0){
            
            String str12="0"+GetBinary32(result1);
            result._s=1;
            String posE=""+str12.charAt(1)+str12.charAt(2)+str12.charAt(3)+str12.charAt(4)+str12.charAt(5)+str12.charAt(6)
                    +str12.charAt(7)+str12.charAt(8);
            int val=Integer.parseInt(posE, 2);
      
            result._e=val;
            String posM=""+str12.charAt(9)+str12.charAt(10)+str12.charAt(11)+str12.charAt(12)+str12.charAt(13)+str12.charAt(14)
                    +str12.charAt(15)+str12.charAt(16)+str12.charAt(17)+str12.charAt(18)+str12.charAt(19)+str12.charAt(20)
                    +str12.charAt(21)+str12.charAt(22)+str12.charAt(23)+str12.charAt(24)+str12.charAt(25)+str12.charAt(26)
                    +str12.charAt(27)+str12.charAt(28)+str12.charAt(29)+str12.charAt(30)+"0";
            int val1=Integer.parseInt(posM, 2);
        
            result._f=val1;
         
            }   
            else{
            String str12=""+GetBinary32(result1);
            result._s=-1;
            String posE=""+str12.charAt(1)+str12.charAt(2)+str12.charAt(3)+str12.charAt(4)+str12.charAt(5)+str12.charAt(6)
                    +str12.charAt(7)+str12.charAt(8);
            int val=Integer.parseInt(posE, 2);
            
            result._e=val;
            String posM="1"+str12.charAt(9)+str12.charAt(10)+str12.charAt(11)+str12.charAt(12)+str12.charAt(13)+str12.charAt(14)
                    +str12.charAt(15)+str12.charAt(16)+str12.charAt(17)+str12.charAt(18)+str12.charAt(19)+str12.charAt(20)
                    +str12.charAt(21)+str12.charAt(22)+str12.charAt(23)+str12.charAt(24)+str12.charAt(25)+str12.charAt(26)
                    +str12.charAt(27)+str12.charAt(28)+str12.charAt(29)+str12.charAt(30)+str12.charAt(31)+"00";
            int val1=Integer.parseInt(posM, 2);
            result._f=val1;

            }

		return result.asInt();
	} 
        private static String GetBinary32( float value )  
    {  
        int intBits = Float.floatToIntBits(value); 
        String binary = Integer.toBinaryString(intBits);
        return binary;
    }
        private static float GetFloat32( String Binary )  
    {  
        int intBits = Integer.parseInt(Binary, 2);
        float myFloat = Float.intBitsToFloat(intBits);
        return myFloat;  
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
