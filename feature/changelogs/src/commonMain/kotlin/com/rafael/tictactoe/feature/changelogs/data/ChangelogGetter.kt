package com.rafael.tictactoe.feature.changelogs.data

expect fun getChangelogFiles(): List<Pair<String, String>>

expect fun getCurrentChangelogFile(): Pair<String, String>
