
package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class VarastoTest {
 
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
 
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
 
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
 
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
 
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
 
        double saatuMaara = varasto.otaVarastosta(2);
 
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
 
        varasto.otaVarastosta(2);
 
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottamisenJalkeenOikeaSaldo() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);
        
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void nollastaNollanKokoinenVarasto() {
        varasto = new Varasto(0);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisestaNollanKokoinenVarasto() {
        varasto = new Varasto(-10);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoOikeinKunTilavuusSuurempi() {
        varasto = new Varasto(10, 3);
        
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void saldoOikeinKunTilavuusPienempi() {
        varasto = new Varasto(10, 11);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisestaAlkusaldostaNolla() {
        varasto = new Varasto(10, -5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisestaTilavuudestaAlkusaldoNolla() {
        varasto = new Varasto(-5, 5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisestaTilavuudestaNolla() {
        varasto = new Varasto(-5, 5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisataJosEiTilaa() {
        varasto.lisaaVarastoon(13);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void eiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-9);
        
        assertEquals(0, varasto.getSaldo(),vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranOttaminenPalauttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaNegatiivistaMaaraa() {
        varasto.otaVarastosta(-5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaEnemmänKuinOn() {
        varasto.lisaaVarastoon(6);
        varasto.otaVarastosta(10);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void paljonkoMahtuuPalauttaaOikeanMaaran() {
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
}