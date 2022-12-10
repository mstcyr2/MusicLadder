package com.example.finalproject.assets

import android.database.sqlite.SQLiteException
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun DatabaseConnection(vm: AppViewModel) {
    val dbHandler = DatabaseHandler(LocalContext.current)

    dbHandler.createMainTables() // TODO: REMOVE THIS AFTER DEBUG

    try {
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
    }

    try {
        dbHandler.addNewSong(
            "Escapism",
            "RAYE, 070 SHAKE",
            "pop",
            0,
            "https://open.spotify.com/track/5Z2MiIZ5I3jJvvmeWMLbOQ?si=c16c71376d504bd0"
        )

        dbHandler.addNewSong(
            "Nonsense",
            "Sabrina Carpenter",
            "pop",
            0,
            "https://open.spotify.com/track/6dgUya35uo964z7GZXM07g?si=1dca752450c1495d"
        )

        dbHandler.addNewSong(
            "Sorry To Me Too",
            "Julia Michaels",
            "pop",
            0,
            "https://open.spotify.com/track/4SDccIA3pVkjaZsivDPaV5?si=5eab6b73d6b44878"
        )

        dbHandler.addNewSong(
            "Pointless",
            "Lewis Capaldi",
            "pop",
            0,
            "https://open.spotify.com/track/4JBiO7wRnE6ueszEUpo347?si=363131dde1e2480f"
        )

        dbHandler.addNewSong(
            "One Life",
            "Dermot Kennedy",
            "pop",
            0,
            "https://open.spotify.com/track/6bB4AiK5tH13695FcNGjDY?si=f90ced27b0ef4adf"
        )
    } catch (e: SQLiteException) {
        // Log.d("MusicLadder", e.toString())
    }

    //vm.setCurrentUserId("user_1") // TODO MOST IMPORTANT FOR WHEN SOMEONE LOGS IN
}