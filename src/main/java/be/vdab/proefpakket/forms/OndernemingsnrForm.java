package be.vdab.proefpakket.forms;

import be.vdab.proefpakket.constraints.Ondernemingsnr;

import javax.validation.constraints.NotNull;

public class OndernemingsnrForm {
    @NotNull
    @Ondernemingsnr
    private final Long ondernemingsnr;

    public OndernemingsnrForm(@NotNull Long ondernemingsnr) {
        this.ondernemingsnr = ondernemingsnr;
    }

    public Long getOndernemingsnr() {
        return ondernemingsnr;
    }
}
