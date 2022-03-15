package com.iqbalfauzi.data.mapper

import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.model.user.UserEntity

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
fun UserEntity.toSingleEntity(): UserData {
    return UserData(
        id,
        name,
        username,
        phone,
        email,
        website,
        address.city,
        address.geo.lat,
        address.geo.lng,
        address.street,
        address.suite,
        address.zipcode,
        company.bs,
        company.catchPhrase,
        company.name
    )
}