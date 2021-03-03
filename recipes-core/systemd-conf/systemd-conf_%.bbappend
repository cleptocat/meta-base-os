FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://eth.network \
"

FILES_${PN} += " \
    ${sysconfdir}/systemd/network/eth.network \
"

do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network

    # Remove default network target files which interfere with ours
    rm -rf ${D}${systemd_unitdir}/network
}
