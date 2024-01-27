/*
 * Created by Bawb
 * 
 * 
 */
package webserver.tests;

/**
 *
 * @author MeanC
 */
public class RandomData {
   
    public static String[] NAMES = {
        "Abby Novak","Adrienne Carson","Alec Wagner","Allen Zimmerman","Humberto Pascual","Alvin Chipmunk","Alyssa Johnston","Amalia Bernard","Amy Moody","Andrea Stewart",
        "Angelica Elliott","Angelica Pickles","Angus Young","Anita Joseph","Ann Stein","Arron Novak","Aubrey Rice","Axl Rose","Beau Walter","Belinda Jefferson",
        "Bennett Ryan","Benson Dunwoody","Beryl Ritter","Bradford Graves","Bridgette Contreras","Jack Black","Caroline Berry","Caroline Zavala","Cedric Davis","Charlie Brown",
        "Cheryl Henry","Chuck Norris","Chuck Testa","Chuck Woods","Clancy Wiggum","Clark Knight","Claudine Norman","Clay Clayton", "Colette Gaines","Columbus Brandt","Constance Bates",
        "Damon Orozco","Darin Hobbs","Dave Grohl","David Buckner","David Gilmour","Deandre Leach","Deborah Wade","Dianna Boone","Dominique Moreno","Dong Ramos",
        "Donn Mcconnell","Dryden Mitchell","Dylan Rowland","Efrain Castaneda","Elaine Wilkerson","Eldridge Key","Ellis Webb","Eric Cartman","Erin Sandoval","Ernest Hester",
        "Eugene Krabs","Eunice Young","Faith Mcpherson","Foster Benson","Frederick Becker","George Jetson","George Washington","Giles Corey","Gordon Ramsay", "Graciela Crosby","Hai Figueroa",
        "Harvey Barker","Heinz Doofenshmirtz","Homer Simpson","Houston Bowman","Hugo Huerta","Ilene Gregory","Issac Vance","Jacoby Shaddix","Jana Cole","Janet Schmitt",
        "Janis Reynolds","Jasper Dominguez","Jay Leno","Jerald Atkins","Jeremy Soto","Jody Compton","Johnnie Burke","Josephine Mcgee","Hector Martinez","Junior Logan",
        "Katelyn Calderon","Kelley Rose","Kenneth Everett","Kyle Brofloski","Larry Moses","Lauren Armstrong","Laurence Whitaker","Lawanda Duarte","Lester Bowers","Lindsay Chan",
        "Lino Singleton","Liz Odonnell","Lonnie Owens","Lucinda Alexander","Lynette Mason","Mai Pineda","Marion Montes","John Lennon","Martina Mason","Marvin Martian",
        "Maxine English","Meghan Bell","Melody Hancock","Merle Morse","Merlin Patterson","Merrill Jordan","Michal Pham","Miriam Duffy","Miriam Lucero","Monty Palmer",
        "Morty Smith","Ned Flanders","Ned Lamb","Noel Mejia","Nona Blair","Oren Lucas","Oswaldo Johnson","Pam Norris","Patricia Blake","Patricia Gamble",
        "Peter Griffin","Phineas Flynn","Queen Holmes","Quinn Vazquez","Ray Salas","Reggie Mahoney","Rick Sanchez","Richie Hensley","Rickey Bray","Barack Obama",
        "Rico Fitzpatrick","Robin Patterson","Ronald Swanson","Rosa Jimenez","Rosemarie Buck","Roxie Alvarez","Ruben Church","Ruthie Ponce","Sallie Gates","Sandy Brady",
        "Santo Tran","Sasha Mayer","Saul Goodman","Saul Nielsen","Scot Mccullough","Seymore Skinner","Juan Diaz","Spencer Maddox","Stacie Case","Taylor Fitzpatrick",
        "Theo Li","Tracy Shea","Trent Quinn","Troy Vazquez","Tye Zamora","Tyree Higgins","Victoria Hensley","Victoria Schneider","Vilma Skinner","Vito Stanton"
    };

    public static String[] STREETS = {
        "5th Avenue", "Union Street", "School Street", "Eagle Street", "Railroad Street", "Arlington Avenue", "North Street", "Lake Street", "State Street", "Somerset Drive", 
        "Fieldstone Drive", "Brandywine Drive", "Hillside Avenue", "Route 9", "Lafayette Street", "Evergreen Lane", "6th Street West", "Church Street North", "Surrey Lane", "Andover Court", 
        "Lake Avenue", "3rd Street North", "Route 1", "Grand Avenue", "Devon Court", "Sycamore Street", "Hamilton Road", "Route 30", "Hillcrest Drive", "East Avenue", "Rose Street", 
        "3rd Street", "Front Street South", "9th Street", "Briarwood Drive", "Magnolia Avenue", "Forest Drive", "Linda Lane", "Park Drive", "Old York Road", "Bay Street", 
        "Essex Court", "Oak Street", "Franklin Street", "Charles Street", "Liberty Street", "Laurel Drive", "Durham Road", "2nd Street North", "Strawberry Lane", "Church Road", 
        "Sherwood Drive", "Monroe Drive", "Green Street", "Carriage Drive", "Williams Street", "Shady Lane", "York Road", "Elm Avenue", "Harrison Avenue", "Jones Street", "Harrison Street", 
        "Crescent Street", "5th Street South", "2nd Avenue", "5th Street North", "Bank Street", "3rd Street East", "Holly Court", "New Street", "Lexington Court", "Briarwood Court", 
        "Linden Avenue", "Orchard Lane", "Sheffield Drive", "Adams Street", "Laurel Street", "Broad Street West", "Belmont Avenue", "South Street", "Beech Street", "Circle Drive", "Port Town Aero Drive",
        "Division Street", "Durham Court", "Chestnut Street", "Parker Street", "Warren Avenue", "Lafayette Avenue", "Elm Street", "Jefferson Court", "Brown Street", "Beechwood Drive", 
        "Main Street East", "7th Avenue", "Bridle Lane", "Main Street North", "4th Street", "Park Place", "Lincoln Avenue", "Glenwood Drive", "Cross Street", "Berkshire Drive", "Broad Street", 
        "Academy Street", "Railroad Avenue", "Grove Avenue", "Tanglewood Drive", "Windsor Court", "Lincoln Street", "Country Club Drive", "Market Street", "Hawthorne Avenue", "Creek Road", 
        "Hudson Street", "Manor Drive", "Hickory Street", "Bridle Court", "Maple Street", "Cambridge Court", "Court Street", "Willow Drive", "12th Street East", "State Street East", "William Street", 
        "Sherman Street", "James Street", "Virginia Avenue", "Lexington Drive", "2nd Street West", "Cambridge Road", "8th Street West", "Lilac Lane", "Grant Avenue", "Hillcrest Avenue", 
        "Cobblestone Court", "Laurel Lane", "Edgewood Drive", "Aspen Court", "Elmwood Avenue", "Westminster Drive", "Lantern Lane", "Valley Road", "Virginia Street", "Ridge Road", "Meadow Lane", 
        "Fairway Drive", "Route 20", "3rd Street West", "Brookside Drive", "Marshall Street", "Valley Drive", "Penn Street", "Hawthorne Lane", "Main Street", "Fawn Court", "Sunset Drive", 
        "Cardinal Drive", "Glenwood Avenue", "Broadway", "Primrose Lane", "Pine Street", "Cherry Lane", "5th Street West", "John Street", "Cleveland Street", "Heather Lane", "Summit Avenue", 
        "Franklin Avenue", "4th Street North", "Warren Street", "Willow Street", "Woodland Avenue", "Summer Street", "Holly Drive", "Clay Street", "Cemetery Road", "Ivy Court", "Adams Avenue", 
        "Overlook Circle", "Madison Avenue", "13th Street", "Locust Lane", "Canterbury Road", "Sycamore Drive", "Pennsylvania Avenue", "Euclid Avenue", "Mill Street", "Chestnut Avenue", 
        "Front Street", "Route 202", "Water Street", "Cypress Court", "6th Street", "George Street", "Ridge Avenue", "Columbia Street", "Pin Oak Drive", "Aspen Drive", "1st Street", "Mulberry Street"
    };

    public static String[] CITIES = {
        "Evergreen Heights", "Silverbrook", "Havenfield", "Willowdale", "Stonewall", "Amberwood", "Riverside", "Maplewood", "Sunnyvale", "Whispering Pines", "Meadowbrook",
        "Harborview", "Oakridge", "Springfield", "Pleasantville", "Fairhaven", "Goldenleaf", "Emerald City", "Crystal Springs", "Moonlight Bay", "Starling Heights", "Cedarville", 
        "Mistwood", "Rosewood", "Avalon", "Lakeside", "Shadowbrook", "Windsor", "Crimson Hollow", "Whitewood", "Harmony Falls", "Silvershore", "Willowbrook", "Hillcrest", "Morningside", 
        "Fairview", "Briarwood", "Serenity Springs", "Pinehurst", "Hazelwood", "Mystic Falls", "Amethyst City", "Coral Bay", "Moonstone", "Ivoryville", "Sapphire Springs", 
        "Emberfield", "Crescent Heights", "Whisperwind", "Harmonyville", "Silverlake", "Meadowlark", "Rosemont", "Havenbrook", "Maplehurst", "Stardust", "Crystalville", "Misthaven", 
        "Willowmere", "Fairfield", "Goldenwood", "Emerald Springs", "Moonlit Meadows", "Starlight City", "Cedarwood", "Shadowvale", "Windsong", "Crimsonville", "Whitestone", 
        "Harmony Hills", "Silvertown", "Willowgrove", "Hillside", "Morningstar", "Fairhaven", "Briarfield", "Serenity Bay", "Pinegrove", "Hazelbrook", "Mystic Springs", "Amberfield", 
        "Coralville", "Moonlight Falls", "Ivory Springs", "Sapphire Bay", "Emberdale", "Crescent Springs", "Whispering Oaks", "Harmony Springs", "Silverwood", "Meadowview", 
        "Roseville", "Havenhurst", "Maplewood", "Stardale", "Crystal Falls", "Mistville", "Willowdale", "Fairview", "Goldenvale"
    };

    public static String[] STATES = {
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
        "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
        "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", 
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" 
    };
    
}
