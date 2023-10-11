SUMMARY = "Initialize Dapr"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/dapr/installer-bundle/releases/download/v1.11.3/daprbundle_linux_arm64.tar.gz"
SRC_URI[sha256sum] = "ab690d876c5427c1143f5a4e2859874bf1f4fdb956cc2c00666c122af4f4bd70"

S = "${WORKDIR}"

do_install () {
  install -d ${D}${bindir}
  install -m 0755 ${S}/daprbundle/dapr ${D}${bindir}
  install -d ${D}/opt
  install -d ${D}/opt/daprbundle
  cp -r ${S}/daprbundle/README.txt ${S}/daprbundle/details.json ${S}/daprbundle/dist ${D}/opt/daprbundle
}

pkg_postinst_ontarget:${PN} () {
  export HOME=/home/root
  dapr init --slim --from-dir /opt/daprbundle
}

FILES:${PN} = "${bindir} /opt"
