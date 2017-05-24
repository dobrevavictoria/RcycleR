package com.example.pc.rcycler;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class Try extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;


    private String economy="Рециклирането носи ползи на икономиката. В много страни компаниите разчитат на програми за рециклиране, които да им предоставят необработени материали за производството. В САЩ например тази индустрия възлиза на $236 милиарда печалба годишно.";
     private String employee="Благодарение на него се създават много работни места. В САЩ повече от 56, 000 предприятия имат 1.1 милиона служители, отговарящи за транспортирането, сортирането и вкарването отново в употреба на отпадъчните суровини. ";
    private String environment="Рециклирането е благоприятно за оконата среда. Хаби по-малко енергия, изисква по-малко естествени ресурси и предотвратява трупането на боклуци в депата за отпадъци. ";
    private String energy_save="Пести значително количество енергия в сравнение с производство, което използва необработени суровини.\n" +
            "Например рециклирането на алуминиеви кенчета спестява 95% енергия.";
    private String global_warming="През 2000, рециклирането на твърди отпадъци \"спести\" на въздуха " +
            "32 900 000 тона въглероден диоксид.";
    private String waste="Благодарение на рециклирането замърсяването на водните ресурси е " +
            "значително по-малко отколкото при производство с необработени суровини.";
    private String wildlife="Използването на рецклирани материали намалява нуждата от унищожаването на гори, " +
            "влажни зони и други ареали за животнските видове.";
    private String landfill="Предотвратява от създаването на депа за отпадъци, което рефлектира в една по-чиста и благоприятна за живот околна среда.";
    private String garbage="Намалява количеството отпадъци. Всеки от нас изхвърля средно по 3,5 килограма боклуци на ден, като по-голямата част от тях се изгарят в депата за отпадъци.";

    private String food="Не оставяйте за рециклиране нищо, по което има остатъци от храна. Хранителните вещества водят до нежелани примеси при производството на продукти от рециклирани материали, което ги прави негодни за употреба. Вместо да хвърляте цялата кутия от пица, откъснете капака и всички незамърсени части и предайте за рециклиране само тях.";
   private String wash="Мийте всичко. Рециклирането е по-ефективно, ако предадените предмети са предварително измити. Не прекалявайте с търкането, просто изплакнете съдовете и отстранете хартиените етикети от стъклените или пластмасовите бутилки.";
    private String factory="Проверете какъв тип пластмаса може да рециклира местният завод за преработка на отпадъци. Съществуват различни видове пластмаса и не всички от тях могат да бъдат рециклирани. Проверете номера, изписан в символа за рециклиране върху продукта и предавайте само пластмаса, която може да бъде преработена.";
    private String paper="Внимавайте с хартията. Не предавайте за рециклиране твърде ярко оцветена хартия, както и восъчна хартия или кaдастрон. Най-ефективно се рециклират стари тетрадки или хартия за принтер. Не си играйте да махате прозрачните найлонови „прозорчета” на пликовете, те не пречат на рециклирането.";
    private String bag="Не изхвърлайте найлоновите торбички в контейнерите за рециклиране. Вместо това проверете дали в квартала ви има супермаркет, който ги събира и рециклира. Или най-добре си вземете платнена торичка за многократна употреба.";
     private String bottle= "Махайте капачките на бутилките за еднократна употреба. Те не могат да бъдат рециклирани. Когато попаднат в завода за преработка на отпадъци, бутилките, които все още имат капачки се изхвърлят вместо да се рециклират, защото работника би изгубил твърде много време в махане на капачката.";

    private String can_energy="Рециклирането на едно алуминево кенче спестява точно толкова енергия, колкото е необходима за 3-часово гледане на телевизия или слушането на един музикален албум на MP3 плеър, а енергията, спестена от 100 такива кенчета, е достатъчна да осветява спалнята ти в продължение на цели две седмици. ";
   private String can_price="Според CRI(Container Recycling Institute) 36-те милиона алуминиеви кенчета, депонирани миналата година, са имали стойност от над $600,000,000 като вторични суровини.";
   private String nyTimes="Рециклирането на един неделен тираж на New York Times , който възлиза на 1,1 милиона броя, би съхранил 75,000 дървета. ";
private String glass_bottle="Разграждането на една „модерна“ стъклена бутилка отнема поне 4000 години, а ако тя се намира на сметище за отпадъци, са й нужни 1 милион години. ";
    private String garbage_amount="За последните 100 години количеството отпадъци, които човечеството е създало, се е увеличило със 10,000%.";
    private String nine_of_ten="Девет от десет души споделят, че биха рециклирали, ако самият процес е по-лесен. \n";
    private static final Integer[] why_image= {
            R.drawable.money_bag256,
            R.drawable.user,
            R.drawable.earth_day256,
            R.drawable.save_energy256,
            R.drawable.global_warming256,
            R.drawable.waste256,
            R.drawable.whale256,
            R.drawable.forest256,
            R.drawable.recycling_bin256
            };
    private final String[] stringArray={
            economy,employee, environment, energy_save, global_warming, waste, wildlife,landfill, garbage};
    private final String[] howTo_text = {bottle, food, wash, factory, paper, bag};
    private static final Integer[] howTo_image = {R.drawable.bottle256,
            R.drawable.food256,
            R.drawable.waste256,
            R.drawable.factory256,
            R.drawable.paper256_light,
            R.drawable.bag256};
    private static final Integer[] facts_image={R.drawable.can_energy2,
            R.drawable.can_price,
            R.drawable.newspaper,
            R.drawable.glass_bottle,
            R.drawable.garbage_amount,
            R.drawable.team
    };
    private final String[] facts_text={can_energy,can_price, nyTimes, glass_bottle, garbage_amount,nine_of_ten};
    private ArrayList<Integer> howTo_array = new ArrayList<Integer>();
    private ArrayList<Integer> facts_array=new ArrayList<Integer>();

    private ArrayList<Integer> why_imageArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);
        switch(Number.getNum()){
            case 1: init1(); break;
            case 2: init2(); break;
            case 3: init3(); break;
    }
    }
    private void init1() {
        for(int i=0;i<facts_image.length;i++)
            facts_array.add(facts_image[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Try.this,facts_array,facts_text));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
    private void init2() {
        for(int i=0;i<howTo_image.length;i++)
            howTo_array.add(howTo_image[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Try.this,howTo_array,howTo_text));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }


    private void init3() {
        for(int i=0;i<why_image.length;i++)
            why_imageArray.add(why_image[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Try.this,why_imageArray,stringArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }


}

        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == why_image.length) {
//                    currentPage = 0;
//                }
//                mPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500, 2500);

