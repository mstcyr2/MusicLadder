/*
package com.example.finalproject.assets
*/


import android.database.sqlite.SQLiteException
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.ui.viewmodel.AppViewModel
import kotlinx.coroutines.launch

@Composable
fun DatabaseConnection(vm: AppViewModel) {

/*    try {
        dbHandler.addNewSong(
            "Superhero (Heroes & Villains)",
            "Metro Boomin, Future, Chris Brown",
            "rap",
            0,
            "https://open.spotify.com/track/0vjeOZ3Ft5jvAi9SBFJm1j?si=fe0ac0217f0d4225"
        )

        dbHandler.addNewSong(
            "Rich Flex",
            "Drake, 21 Savage",
            "rap",
            0,
            "https://open.spotify.com/track/1bDbXMyjaUIooNwFE9wn0N?si=0028bba9598b4128"
        )

        dbHandler.addNewSong(
            "To The Bone",
            "Quavo, Takeoff, YoungBoy",
            "rap",
            0,
            "https://open.spotify.com/track/4wRJHXHDJnKSPr9IVn0BFR?si=6c882032eae642ad"
        )

        dbHandler.addNewSong(
            "Niagara Falls",
            "Metro Boomin, Travis Scott, 21 Savage",
            "rap",
            0,
            "https://open.spotify.com/track/4WuOWVnAqvEQxgSRrspBgt?si=4c4d8ecbd72a43a9"
        )

        dbHandler.addNewSong(
            "Hell Yeah",
            "SoFaygo, Ken Carson",
            "rap",
            0,
            "https://open.spotify.com/track/4WuOWVnAqvEQxgSRrspBgt?si=401c61fd839848f3"
        )

        dbHandler.addNewSong(
            "One Up",
            "Central Cee",
            "rap",
            0,
            "https://open.spotify.com/track/6R6ZS5HYH4RdXkjEwEJO5R?si=01a131a8c7ff4585"
        )

        dbHandler.addNewSong(
            "Letter to Takeoff",
            "Gucci Mane",
            "rap",
            0,
            "https://open.spotify.com/track/7B09THlbQE2RndpgXeXQYE?si=45df0b62186741c1"
        )

        dbHandler.addNewSong(
            "Not Finished",
            "Lil Baby",
            "rap",
            0,
            "https://open.spotify.com/track/2A0G0bPmlkUXUbvWqrAXzg?si=a2cd525bc9e54f5d"
        )

        dbHandler.addNewSong(
            "Raindrops",
            "Metro Boomin, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/1pacwLXyRO47ka0v6LTIiY?si=83781a05c6d94ade"
        )

        dbHandler.addNewSong(
            "One Mic, One Gun",
            "Metro Boomin, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/7CpXFEjLntpr4GdOhTtFEv?si=a9a5640760db41ee"
        )

        dbHandler.addNewSong(
            "Just Wanna Rock",
            "Lil Uzi Vert",
            "rap",
            0,
            "https://open.spotify.com/track/4FyesJzVpA39hbYvcseO2d?si=2129b1cea8714ec0"
        )

        dbHandler.addNewSong(
            "Do It Again",
            "NLE Choppa, 2Rare",
            "rap",
            0,
            "https://open.spotify.com/track/5gAwpwuchaCGnJLlBMGBzp?si=2d84b1bbc94a471b"
        )

        dbHandler.addNewSong(
            "Pussy & Millions",
            "Drake, 21 Savage, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/2KLwPaRDOB87XOYAT2fgxh?si=f5efaa500a0347d0"
        )

        dbHandler.addNewSong(
            "Jigsaw",
            "Key Glock",
            "rap",
            0,
            "https://open.spotify.com/track/3Wqs6XFKsKcTjaWhmB8VCP?si=9f92f780037a46ac"
        )

        dbHandler.addNewSong(
            "Ain't Safe",
            "Trippie Redd",
            "rap",
            0,
            "https://open.spotify.com/track/6ja11GoXgF75QkEVqqAadn?si=6194811457cf49c7"
        )

        dbHandler.addNewUser(
            "admin",
            "password"
        )
    } catch(e : SQLiteException) {
        // Log.d("MusicLadder", e.toString())
    }*/

    LaunchedEffect(vm.viewModelScope) {
        try {

            vm.addNewSong(
                "pop_11",
                "This Hell",
                "Rina Sawayama",
                "pop",
                "https://open.spotify.com/track/6A6yKpsgFr4gIKd7gsfHhm?si=8f7169bc12554ae6"
            )

            vm.addNewSong(
                "pop_12",
                "Attention",
                "NewJeans",
                "pop",
                "https://open.spotify.com/track/2pIUpMhHL6L9Z5lnKxJJr9?si=8249410ddc444e2a"
            )

            vm.addNewSong(
                "pop_13",
                "Cuff It",
                "Beyonce",
                "pop",
                "https://open.spotify.com/track/1xzi1Jcr7mEi9K2RfzLOqS?si=c86aa6696bb3472b"
            )

            vm.addNewSong(
                "pop_14",
                "Wild Flower",
                "RM, youjeen",
                "pop",
                "https://open.spotify.com/track/5p8ThxM2OhJ0igfxkz0Z1q?si=0e257c8698c24181"
            )

            vm.addNewSong(
                "pop_15",
                "Shirt",
                "SZA",
                "pop",
                "https://open.spotify.com/track/34ZAzO78a5DAVNrYIGWcPm?si=8a985966f72d47de"
            )

            vm.addNewSong(
                "rock_1",
                "The News",
                "Paramore",
                "rock",
                "https://open.spotify.com/track/02oTRx0v3GloOe5VJZgjC7?si=49e3c8cd5a7a4fa5"
            )

            vm.addNewSong(
                "rock_2",
                "Emergency Contact",
                "Pierce The Veil",
                "rock",
                "https://open.spotify.com/track/5xJlzQiPLYkvlqkRPKzBwD?si=ba48d258f22f436a"
            )

            vm.addNewSong(
                "rock_3",
                "sTraNgeRs",
                "Bring Me The Horizon",
                "rock",
                "https://open.spotify.com/track/5fpq1wF8xa5tSSlcKHdmGQ?si=55a9bd163e324d7f"
            )

            vm.addNewSong(
                "rock_4",
                "I'm a Mess",
                "Avril Lavigne, YUNGBLUD",
                "rock",
                "https://open.spotify.com/track/77zu6q8jHBRPJSJRu6rsTf?si=3c808226b9014ff3"
            )

            vm.addNewSong(
                "rock_5",
                "curious/furious",
                "WILLOW",
                "rock",
                "https://open.spotify.com/track/2OdRGWLkvSccWOrYFlbIFF?si=4d3c4f5827574257"
            )

            vm.addNewSong(
                "rock_6",
                "This Is Why",
                "Paramore",
                "rock",
                "https://open.spotify.com/track/7z84Fwf1R3Z2BwHCP620CI?si=f3aa026388184906"
            )

            vm.addNewSong(
                "rock_7",
                "THE LONELIEST",
                "Maneskin",
                "rock",
                "https://open.spotify.com/track/1Ame8XTX6QHY0l0ahqUhgv?si=7ce56664f7154243"
            )

            vm.addNewSong(
                "rock_8",
                "I'm In Love With You",
                "The 1975",
                "rock",
                "https://open.spotify.com/track/0uBdQzKghx88d2Lp8SLFKJ?si=7b0ea9136d96474f"
            )

            vm.addNewSong(
                "rock_9",
                "Crazy On You",
                "Heart",
                "rock",
                "https://open.spotify.com/track/5zH710lFSLtkHbMkslLDjR?si=7c128b438cf146f9"
            )

            vm.addNewSong(
                "rock_10",
                "Hello You",
                "Arctic Monkeys",
                "rock",
                "https://open.spotify.com/track/5ygEUpyZy5qtZ1423zymBW?si=6cfb2be7bc7e4972"
            )

            vm.addNewSong(
                "rock_11",
                "Frankenstein",
                "Rina Sawayama",
                "rock",
                "https://open.spotify.com/track/4nYUJ36Qj9hIY9mKj8QZFF?si=d18ab1eefc764f70"
            )

            vm.addNewSong(
                "rock_12",
                "The Astronaut",
                "JIN",
                "rock",
                "https://open.spotify.com/track/0h7QMc9ZRzA9QJrbEHytn2?si=aa02af4ca8a84d3c"
            )

            vm.addNewSong(
                "rock_13",
                "Obsession : SOLAR",
                "ALT BLK ERA",
                "rock",
                "https://open.spotify.com/track/69uILpq1JLF3lzZkmwssNu?si=5e8fb281768b461d"
            )

            vm.addNewSong(
                "rock_14",
                "Broken Pieces Shine",
                "Evanescence",
                "rock",
                "https://open.spotify.com/track/7mH6yI428XqzHkPGtkSMBj?si=36e277881a994d88"
            )

            vm.addNewSong(
                "rock_15",
                "A Match Into Water",
                "Pierce The Veil",
                "rock",
                "https://open.spotify.com/track/54MXF9I8s3DuiQo3g0gZ5k?si=6d3108062d6e490f"
            )


        } catch (e: SQLiteException) {
            // Log.d("MusicLadder", e.toString())
        }
    }


    //vm.setCurrentUserId("user_1") // TODO MOST IMPORTANT FOR WHEN SOMEONE LOGS IN
}

