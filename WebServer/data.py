import random, datetime

today = datetime.datetime.now()

people = [
        "Abby Novak","Adrienne Carson","Alec Wagner","Allen Zimmerman",
        "Humberto Pascual","Alvin Chipmunk","Alyssa Johnston","Amalia Bernard",
        "Amy Moody","Andrea Stewart","Angelica Elliott","Angelica Pickles",
        "Angus Young","Anita Joseph","Ann Stein","Arron Novak","Aubrey Rice",
        "Axl Rose","Beau Walter","Belinda Jefferson","Bennett Ryan","Benson Dunwoody",
        "Beryl Ritter","Bradford Graves","Bridgette Contreras","Jack Black",
        "Caroline Berry","Caroline Zavala","Cedric Davis","Charlie Brown","Cheryl Henry",
        "Chuck Norris","Chuck Testa","Chuck Woods","Clancy Wiggum","Clark Knight","Claudine Norman",
        "Clay Clayton", "Colette Gaines","Columbus Brandt","Constance Bates","Damon Orozco",
        "Darin Hobbs","Dave Grohl","David Buckner","David Gilmour","Deandre Leach","Deborah Wade",
        "Dianna Boone","Dominique Moreno","Dong Ramos","Donn Mcconnell","Dryden Mitchell",
        "Dylan Rowland","Efrain Castaneda","Elaine Wilkerson","Eldridge Key","Ellis Webb","Eric Cartman",
        "Erin Sandoval","Ernest Hester", "Eugene Krabs","Eunice Young","Faith Mcpherson","Foster Benson",
        "Frederick Becker","George Jetson","George Washington","Giles Corey","Gordon Ramsay", 
        "Graciela Crosby","Hai Figueroa", "Harvey Barker","Heinz Doofenshmirtz","Homer Simpson",
        "Houston Bowman","Hugo Huerta","Ilene Gregory","Issac Vance","Jacoby Shaddix","Jana Cole","Janet Schmitt",
        "Janis Reynolds","Jasper Dominguez","Jay Leno","Jerald Atkins","Jeremy Soto","Jody Compton",
        "Johnnie Burke","Josephine Mcgee","Hector Martinez","Junior Logan","Katelyn Calderon","Kelley Rose",
        "Kenneth Everett","Kyle Brofloski","Larry Moses","Lauren Armstrong","Laurence Whitaker",
        "Lawanda Duarte","Lester Bowers","Lindsay Chan","Lino Singleton","Liz Odonnell","Lonnie Owens",
        "Lucinda Alexander","Lynette Mason","Mai Pineda","Marion Montes","John Lennon","Martina Mason","Marvin Martian",
        "Maxine English","Meghan Bell","Melody Hancock","Merle Morse","Merlin Patterson","Merrill Jordan",
        "Michal Pham","Miriam Duffy","Miriam Lucero","Monty Palmer","Morty Smith","Ned Flanders","Ned Lamb",
        "Noel Mejia","Nona Blair","Oren Lucas","Oswaldo Johnson","Pam Norris","Patricia Blake","Patricia Gamble",
        "Peter Griffin","Phineas Flynn","Queen Holmes","Quinn Vazquez","Ray Salas",
        "Reggie Mahoney","Rick Sanchez","Richie Hensley","Rickey Bray","Barack Obama",
        "Rico Fitzpatrick","Robin Patterson","Ronald Swanson","Rosa Jimenez","Rosemarie Buck",
        "Roxie Alvarez","Ruben Church","Ruthie Ponce","Sallie Gates","Sandy Brady",
        "Santo Tran","Sasha Mayer","Saul Goodman","Saul Nielsen","Scot Mccullough","Seymore Skinner",
        "Juan Diaz","Spencer Maddox","Stacie Case","Taylor Fitzpatrick",
        "Theo Li","Tracy Shea","Trent Quinn","Troy Vazquez","Tye Zamora","Tyree Higgins","Victoria Hensley",
        "Victoria Schneider","Vilma Skinner","Vito Stanton", "Alice Johnson", "Bob Smith", 
        "Emma Thompson", "James Wilson", "Olivia Brown", "William Davis", 
        "Sophia Martinez", "John Taylor", "Isabella Anderson", "Michael Thomas",
        "Charlotte Garcia", "Daniel Hernandez", "Amelia Lopez", "Matthew Gonzalez", 
        "Mia Perez", "Joseph Moore", "Emily Sanchez", "David Clark", "Abigail Ramirez", "Alexander Lewis",
        "Harper Hall", "Noah Walker", "Elizabeth Young", "Ethan King", "Evelyn Wright", 
        "Liam Hill", "Avery Rodriguez", "Benjamin Martin", "Camila Scott", "Lucas Green",
        "Ella Adams", "Samuel Baker", "Sofia Nelson", "Jackson Martinez", "Avery Evans", "Sebastian Garcia", 
        "Scarlett Brooks", "Gabriel Mitchell", "Lily Rivera", "Ryan Carter",
        "Madison Stewart", "Luke Turner", "Aria Phillips", "Henry Cooper", "Grace Flores", "Owen Reed", 
        "Chloe Bennett", "Wyatt Nelson", "Zoey Stewart", "Carter Flores",
        "Penelope Cooper", "Jayden Martinez", "Riley Hill", "Layla Johnson", "Julian Clark", 
        "Nora Diaz", "Levi White", "Hannah Martinez", "Nathan Russell", "Aubrey Carter",
        "Eli Scott", "Lillian Howard", "Isaac Reed", "Addison Wright", "Daniel Peterson", "Eleanor Perez", 
        "Lincoln Hall", "Natalie Jackson", "Jack Wright", "Brooklyn Smith", "Leo Rodriguez", "Paisley Mitchell", 
        "Caleb Martinez", "Audrey Ramirez", "Hunter Lewis", "Bella Johnson", "Jeremiah Rodriguez", 
        "Skylar Anderson", "Adeline Campbell", "Connor Lewis", "Maya Hernandez", "Davidson Foster", 
        "Lydia Mitchell", "Gavin Collins", "Ellie Murphy", "Nicholas Gonzales", 
        "Stella King", "Elijah Evans", "Violet Thompson", "Jameson Perez", "Makayla White", 
        "Emmett Turner", "Claire Rivera", "Luca Brooks", "Sadie Parker", 
        "Hudson Harris", "Elena Phillips", "Christian Hall", "Hazel Foster", "Shooty Kontofos", "Ping Kowalski"
        ]

Staff_Names = [
        "Taylor Johnson", "Brandon Clark", "Alexis Taylor", "Dylan Walker", "Haley King",
        "Connor Adams", "Madison Baker", "Jordan Scott", "Sydney White", "Austin Martinez",
        "Bailey Ramirez", "Peyton Carter", "Morgan Flores", "Cameron Brooks", "Hayden Cooper",
        "Jordan Rivera", "Taylor Bennett", "Alexis Ross", "Logan Coleman", "Dakota Russell",
        "Alexis Bennett", "Jordan Brooks", "Cameron Martinez", "Morgan Parker", "Peyton Carter",
        "Taylor Gray", "Dylan Rodriguez", "Jordan King", "Logan Hayes", "Hayden Cooper",
        "Taylor Hernandez", "Sydney Turner", "Cameron Harris", "Jordan Richardson", "Taylor Cox",
        "Jordan Gomez", "Bailey Hill", "Dakota Richardson", "Peyton Griffin", "Logan Howard",
        "Morgan Diaz", "Sydney Foster", "Hayden Coleman", "Jordan Perry", "Cameron Evans",
        "Taylor Ortiz", "Dylan Stewart", "Jordan Powell", "Bailey Bryant", "Peyton Mitchell",
        "Logan Simmons", "Morgan Stewart", "Sydney Armstrong", "Hayden Powell", "Cameron Webb",
        "Taylor Simpson", "Jordan Ross", "Bailey Bell", "Dakota Gordon", "Peyton Foster",
        "Logan Oliver", "Morgan Diaz", "Sydney Richardson", "Hayden Turner", "Cameron Cook",
        "Taylor Howard", "Jordan Murphy", "Bailey Diaz", "Dakota Griffin", "Peyton Wallace",
        "Logan Hayes", "Morgan Gray", "Sydney Bryant", "Hayden Richardson", "Cameron Martinez",
        "Taylor Gomez", "Jordan Brooks", "Bailey Russell", "Dakota Bell", "Peyton Simmons",
        "Logan Richardson", "Morgan Coleman", "Sydney Diaz", "Hayden Stewart", "Cameron Powell",
        "Taylor Oliver", "Jordan Armstrong", "Bailey Turner", "Dakota Foster", "Peyton Richardson",
        "Logan Cook", "Morgan Murphy", "Sydney Diaz", "Hayden Gray", "Cameron Bell", "Bob McTest",
        "Thomas Testington", "Petras Testerton", "Gail Test", "Nicola Tesla"
]

streets = [
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
        "Front Street", "Route 202", "Water Street", "Cypress Court", "6th Street", "George Street", "Ridge Avenue", "Columbia Street", "Pin Oak Drive", "Aspen Drive", "1st Street", "Mulberry Street",  "Whimsy Way", "Mystic Lane", "Tranquility Trail", "Blossom Boulevard", "Harmony Street", "Serenity Lane", "Pleasant Place", "Dreamer's Drive", "Peaceful Path", "Gentle Way",
        "Whispering Way", "Sunny Circle", "Cottonwood Court", "Meadowlark Lane", "Wisteria Way", "River's Edge Road", "Lighthouse Lane", "Breezy Bend", "Sycamore Street", "Maple Avenue",
        "Hilltop Road", "Tranquil Terrace", "Pine Street", "Oakwood Avenue", "Majestic Mile", "Golden Gate", "Crystal Cove Road", "Lakeside Drive", "Whispering Willow Walk", "Hazel Lane",
        "Sunset Boulevard", "Harmony Court", "Meadowview Drive", "Whispering Pines Avenue", "Valley View Road", "Mystic Circle", "Enchanted Lane", "Blossom Lane", "Trinity Avenue", 
        "Emerald Street", "Cedar Lane", "Prairie Lane", "Silver Springs Drive", "Greenwood Avenue", "Rosewood Lane", "Peachtree Street", "Crystal Creek Drive", "Majestic View", 
        "Harbor Drive", "Gardenia Lane", "Dreamland Drive", "Shadow Lane", "Sunny Shores Lane", "Crestwood Court", "Tranquility Way", "Meadow Lane", "Golden Lane", "Echo Lane", 
        "Whispering Winds Road", "Starlight Drive", "Tranquil Way", "Harmony Lane", "Serenity Avenue", "Crystal Court", "Bluebird Lane", "Willow Way", "Lighthouse Lane", "Pine Lane", 
        "Golden Gate Avenue", "Cottonwood Lane", "Trinity Lane", "Meadow View Drive", "Blossom Drive", "Maple Lane", "Peachtree Lane", "Emerald Lane", "Silver Springs Lane", 
        "Greenwood Lane", "Rosewood Drive", "Crystal Creek Lane", "Harbor Lane", "Gardenia Drive", "Dreamland Lane", "Shadow Way", "Sunny Shores Drive", "Crestwood Drive", 
        "Tranquility Lane", "Golden Way", "Starlight Lane", "Tranquil Drive", "Harmony Drive", "Serenity Drive", "Crystal Way", "Bluebird Drive", "Willow Lane"
]

cities = [
        "Evergreen Heights", "Silverbrook", "Havenfield", "Willowdale", "Stonewall", "Amberwood", "Riverside", "Maplewood", "Sunnyvale", "Whispering Pines", "Meadowbrook",
        "Harborview", "Oakridge", "Springfield", "Pleasantville", "Fairhaven", "Goldenleaf", "Emerald City", "Crystal Springs", "Moonlight Bay", "Starling Heights", "Cedarville", 
        "Mistwood", "Rosewood", "Avalon", "Lakeside", "Shadowbrook", "Windsor", "Crimson Hollow", "Whitewood", "Harmony Falls", "Silvershore", "Willowbrook", "Hillcrest", "Morningside", 
        "Fairview", "Briarwood", "Serenity Springs", "Pinehurst", "Hazelwood", "Mystic Falls", "Amethyst City", "Coral Bay", "Moonstone", "Ivoryville", "Sapphire Springs", 
        "Emberfield", "Crescent Heights", "Whisperwind", "Harmonyville", "Silverlake", "Meadowlark", "Rosemont", "Havenbrook", "Maplehurst", "Stardust", "Crystalville", "Misthaven", 
        "Willowmere", "Fairfield", "Goldenwood", "Emerald Springs", "Moonlit Meadows", "Starlight City", "Cedarwood", "Shadowvale", "Windsong", "Crimsonville", "Whitestone", 
        "Harmony Hills", "Silvertown", "Willowgrove", "Hillside", "Morningstar", "Fairhaven", "Briarfield", "Serenity Bay", "Pinegrove", "Hazelbrook", "Mystic Springs", "Amberfield", 
        "Coralville", "Moonlight Falls", "Ivory Springs", "Sapphire Bay", "Emberdale", "Crescent Springs", "Whispering Oaks", "Harmony Springs", "Silverwood", "Meadowview", 
        "Roseville", "Havenhurst", "Maplewood", "Stardale", "Crystal Falls", "Mistville", "Willowdale", "Fairview", "Goldenvale", "Rusticville", "Meadowfield", "Sunnyvale", "Greenwood", "Pinecrest", "Whispering Pines", "Hilltopia", "Mystic Springs", "Willow Creek", "Harmony Hills",
        "Mapleton", "Tranquil City", "Oakwood Heights", "Serenity Falls", "Hidden Haven", "Wisteria Lane", "Emerald Springs", "Sycamore Valley", "Hazelwood", "Silver Springs",
        "Breezy Bluffs", "Enchanted Glen", "Copperwood", "Peachtree Heights", "Whimsy Woods", "Evergreen Acres", "Majestic Meadows", "Crestwood", "Golden Grove", "Crystal Cove",
        "Moonbeam Heights", "Wildflower Fields", "Gentle Breeze", "Dreamland Hills", "Whispering Meadows", "Crystal Waters", "Quaint Village", "Harbor Heights", "Gardenia Gardens",
        "Happy Hollow", "Sparkle City", "Rainbow Ridge", "Trinity Terrace", "Rosewood", "Echo Valley", "Lakeside Landing", "Fairview Heights", "Valley View", "Prairieville",
        "Whitewood", "Maplewood", "Pleasant Park", "Fairfield", "Sunny Shores", "Tranquility Bay", "Misty Valley", "Blossom Hill", "Sunset Springs", "Harmony Cove",
        "Amber Acres", "Tranquil Trails", "River's Edge", "Woodland Heights", "Lighthouse Bay", "Pineview", "Silver Lake", "Cottonwood Springs", "Shadow Hills", "Candlewood",
        "Aspen Grove", "Bluebird Meadows", "Ponderosa", "Majestic Hills", "Meadowlark Lane", "Starlight Valley", "Stonegate", "Serene Springs", "Greenfield", "Mossy Oak",
        "Tranquil Lake", "Autumn Ridge", "Woodland Creek", "Morning Mist", "Windy Hill", "Meadowbrook", "Whispering Pines", "Willow Glen", "Pine Ridge", "Serenity Springs",
        "Crystal Springs", "Harmony Hills", "Sunny Valley", "Silver Creek", "Oak Ridge", "Golden Meadow", "Summerfield"
 ]

states = [
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
        "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
        "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", 
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" 
]

sex = ["male", "female"]

pharmacy =  [
        "Walgreens", "CVS Pharmacy", "Rite Aid", "Boots Pharmacy", "Guardian Pharmacy", 
        "MediMart Pharmacy", "HealthFirst Pharmacy", "QuickScript Pharmacy", "CarePlus Pharmacy", "MediCare Pharmacy"
]

primary_languages = [
        "English", "Mandarin Chinese", "Hindi", "Spanish", "French", "Standard Arabic", "Bengali", "Russian", "Portuguese", "Urdu",
        "Indonesian", "German", "Japanese", "Swahili", "Telugu", "Marathi", "Turkish", "Tamil", "Vietnamese", "Korean",
        "Italian", "Yoruba", "Thai", "Gujarati", "Javanese", "Filipino", "Kannada", "Amharic", "Malayalam", "Odia",
        "Punjabi", "Burmese", "Sundanese", "Nepali", "Sinhala", "Slovak", "Malay", "Romanian", "Greek", "Bulgarian",
        "Serbian", "Hungarian", "Czech", "Finnish", "Swedish", "Danish", "Norwegian", "Icelandic", "Lithuanian", "Latvian",
        "Estonian", "Albanian", "Croatian", "Bosnian", "Macedonian", "Slovenian", "Ukrainian", "Belarusian", "Mongolian", "Kazakh",
        "Armenian", "Georgian", "Kyrgyz", "Tajik", "Uzbek", "Turkmen", "Azerbaijani", "Kurdish", "Pashto", "Dari",
        "Tigrinya", "Somali", "Kinyarwanda", "Luganda", "Chichewa", "Zulu", "Xhosa", "Afrikaans", "Khoekhoe", "Sesotho",
        "Setswana", "Swati", "Tsonga", "Venda", "Amharic", "Tigrinya", "Oromo", "Igbo", "Hausa", "Yoruba",
        "Akan", "Wolof", "Fula", "Mandinka", "Malagasy", "Samoan", "Tongan", "Hawaiian", "Maori", "Fijian",
        "Marshallese", "Chamorro", "Palauan", "Nauruan", "Kiribati", "Tuvaluan", "Tokelauan", "Hiri Motu", "Tetum", "Bislama"
]

ethnicities = [
        "African Americans", "Hispanic and Latino Americans", "Puerto Ricans", "Americans", "Pacific Islander", 
        "Native Americans in the United States", "Mexicans", "White people", "Asian people", "Caribbean people", 
        "Latin Americans", "Alaska Natives", "Indigenous peoples", "Arabs", "Middle Eastern", "Native Hawaiians", 
        "Navajo", "English people", "Irish people", "Portuguese people", "Dutch people", "Koreans", "Africans", 
        "Filipino", "Maori people", "Kazakhs", "Kung people", "Basques", "Han Chinese", "Afrikaners", 
        "Assyrian people", "Maya peoples", "Nùng people", "Kongo people", "Yamato people", "Kurds", "Catalans", 
        "Ilocano people", "Cherokee", "Hutu", "Caldoche", "Hani people", "Chumash people", "Aleuts", "Aimaq people", 
        "Bosniaks", "Czechs", "Bulgarians", "Germans", "Manchu people", "Chams", "Lhoba people"
]

races = [
        "Caucasian", "African", "Asian", "Hispanic or Latino", "Native American or Alaska Native", "Native Hawaiian or Other Pacific Islander", 
        "Middle Eastern", "Mixed Race"
]

Health_Insurance = [
        "WellCare Health Insurance", "MediSure Health Insurance", "HealthGuard Insurance", "CareLink Health Insurance", "Vitality Health Insurance", 
        "MediCover Health Insurance", "TrueCare Health Insurance", "CompleteCare Health Insurance", "Optima Health Insurance", "TotalHealth Insurance"
]

#randEmailExt = ["@gmail.com", "@hotmail.com", "@blogspot.com", "@aol.net", "@mailtrap.io", "@outlook.com", "@yahoo.com", "@protonmail.me", "@zoho.com"]

with open("ehs_populate.sql", "w") as sqlFile:
    sqlFile.write("--truncate table `Client`;\n")

    # populate accounts
    for person in people:
        fn, ln = person.split(' ')
        #mn = chr(random.randint(65, 90))
        #email = "{0}{1}".format(fn + mn + ln, randEmailExt[random.randint(0, len(randEmailExt) - 1)]).lower()
        phone = "{0}{1}{2}".format(random.randint(100, 999), random.randint(100, 999), random.randint(1000, 9999))
        dob = "{0}/{1}/{2}".format(random.randint(1, 12), random.randint(1, 31), random.randint(1930, 2023))
        address = "{0} {1}".format(random.randint(1000, 9999), streets[random.randint(0, len(streets) - 1)])
        city = cities[random.randint(0, len(cities) - 1)]
        state = states[random.randint(0, len(states) - 1)]
        zip = random.randint(10000, 99999)
        ethnicity = ethnicities[random.randint(0, len(ethnicities) - 1)]
        sex = sex[random.randint(0, len(sex) - 1)]
        race = races[random.randint(0, len(races) - 1)]
        primary_language = primary_languages[random.randint(0, len(primary_languages) - 1)]
        staff_id  = random.randint(1, len(Staff_Names))
        Health_Insurancealth_Insurance = Health_Insurance[random.randint(0, len(Health_Insurance) - 1)]

        enteredBy = 1
        enteredDate = "{0}/{1}/{2}".format(today.month, today.day, today.year)

        sqlFile.write("insert into `Client` (FirstName, LastName, Phone, DOB, Address, City, State, Zip, EnteredBy, EnteredDate, StaffID, HealthInsuranceProvider, Ethnicity, Sex, Race, PrimaryLanguage)  values('{0}', '{1}', '{2}', '{3}', '{4}', '{5}', '{6}', '{7}', '{8}', '{9}', '{10}', '{11}', '{12}', '{13}', '{14}', '{15}');\n" \
                .format(fn, ln, phone, dob, address, city, state, zip, enteredBy, enteredDate, Staff_Names, Health_Insurance, ethnicity, sex, race, primary_language))