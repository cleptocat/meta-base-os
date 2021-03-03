# This recipe performs the following tasks
# 1) Install .sh file in /home/root/ and .sh script creates a random text file
# 2) Install the .service file in systemd directory
# 3) Invoke the .sh script via .service file
# INITSCRIPT_NAME = "start_script"
inherit update-rc.d systemd

# inherit systemd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SUMMARY = "Install and start a systemd service"
SECTION = "custom"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#here we specify the source we want to import
SRC_URI += "file://start_script.sh"
SRC_URI += "file://start-script.service"
#here we specify the source directory, where we can do all the building and expect sources to be placed
S = "${WORKDIR}"

SYSTEMD_SERVICE_${PN} = "start-script.service"


#bitbake task
#install script in directory ${libexecdir}
do_install_append() {
    install -d ${D}${libexecdir}
    install -m 0755 ${WORKDIR}/start_script.sh ${D}${libexecdir}
    chmod +x ${D}${libexecdir}/start_script.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/start-script.service ${D}${systemd_system_unitdir}
}

#Pack the path
FILES_${PN} += "${libexecdir}/start_script.sh"
FILES_${PN} += "${systemd_system_unitdir}/start-script.service"

# /start_script.sh
