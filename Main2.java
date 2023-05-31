import java.util.Scanner;

abstract class Angka 
{
    abstract Angka tambah(Angka angka);
    abstract void cetak();
}

class Rasional extends Angka 
{
    int pembilang;
    int penyebut;

    Rasional(int pembilang, int penyebut) 
    {
        this.pembilang = pembilang;
        this.penyebut = penyebut;
    }


    int fpb(int input_a, int input_b) 
    {
        if (input_b == 0) 
        {
            return input_a;
        }
        return fpb(input_b, input_a % input_b);
    }


    Angka tambah(Angka angka) 
    {
        if (angka instanceof Rasional) 
        {
            int penyebutUmum = this.penyebut * ((Rasional) angka).penyebut;
            int pembilangBaru = this.pembilang * ((Rasional) angka).penyebut + ((Rasional) angka).pembilang * this.penyebut;
            int fpb = fpb(pembilangBaru, penyebutUmum);
            return new Rasional(pembilangBaru / fpb, penyebutUmum / fpb);
        }
        return null;
    }

    void cetak()
    {
        System.out.printf("%d %d\n", pembilang, penyebut);
    }
}

class Desimal extends Angka 
{

    double nilai;

    Desimal(double nilai) 
    {
        this.nilai = nilai;
    }

    void cetak()
    {
        System.out.printf("%.2f\n", nilai);
    }

    Angka tambah(Angka input_angka) 
    {
        if (input_angka instanceof Desimal) 
        {
            return new Desimal(this.nilai + ((Desimal) input_angka).nilai);
        }
        return null;
    }


}

public class Main
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        int n;
        int p,q,r,s;
        int indeksAwal,indeksAkhir,jenis;
        int i,j;
        n = scan.nextInt();
        Angka[] angkaArr = new Angka[n];

        for (i = 0; i < n; i++) 
        {
            jenis = scan.nextInt();
            if (jenis == 1) 
            {
                angkaArr[i] = new Desimal(scan.nextDouble() + scan.nextDouble());
            } 
            else 
            {
                p = scan.nextInt();
                q = scan.nextInt();
                r = scan.nextInt();
                s = scan.nextInt();
                angkaArr[i] = new Rasional(p, q).tambah(new Rasional(r, s));
            }
        }

        indeksAwal = scan.nextInt();
        indeksAkhir = scan.nextInt();
        for (j = indeksAwal - 1; j < indeksAkhir; j++) 
        {
            angkaArr[j].cetak();
        }
    }
}