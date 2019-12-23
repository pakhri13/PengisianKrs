package net.simplifiedcoding.Biodata

class Biodata (
    val id: String,
//    val uid : String,
    val nama: String,
    val npm: String,
    val lahir: String,
    val photo: String
) {
    constructor() : this("","","","",""){

    }
}