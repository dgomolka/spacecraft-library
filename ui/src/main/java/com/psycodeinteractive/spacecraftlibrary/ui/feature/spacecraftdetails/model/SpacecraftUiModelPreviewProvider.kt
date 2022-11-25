package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.AgencyUiModel.AgencyInfoUiModel

class SpacecraftUiModelPreviewProvider : PreviewParameterProvider<SpacecraftUiModel> {
    private val spacecraft1 = SpacecraftUiModel(
        id = 1,
        url = "www.spacecraft1.com",
        name = "Spacecraft1",
        serialNumber = "123",
        status = StatusUiModel(1, "status1"),
        description = "Description1",
        spacecraftConfig = SpacecraftConfigUiModel(
            id = 1,
            url = "www.config.com",
            name = "spacecraft config 1",
            type = TypeUiModel(1, "type1"),
            agency = AgencyInfoUiModel(
                1,
                "www.imageagency.com",
                "Agency1",
                "agencytype1"
            ),
            isInUse = true,
            imageUrl = "www.imageconfig.com"
        )
    )

    private val spacecraft2 = SpacecraftUiModel(
        id = 1,
        url = "www.spacecraft2.com",
        name = "Spacecraft 2",
        serialNumber = "1234567",
        status = StatusUiModel(2, "status 2"),
        description = "Description 2",
        spacecraftConfig = SpacecraftConfigUiModel(
            id = 1,
            url = "www.config222.com",
            name = "spacecraft config 222",
            type = TypeUiModel(2, "type2"),
            agency = AgencyInfoUiModel(2, "www.imageagency2.com", "Agency2", "agencytype2"),
            isInUse = false,
            imageUrl = "www.imageconfig2.com"
        )
    )

    override val values: Sequence<SpacecraftUiModel> = sequenceOf(
        spacecraft1,
        spacecraft2
    )
}
