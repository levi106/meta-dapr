SUMMARY = "Initialize Dapr"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/dapr/installer-bundle/releases/download/v1.11.3/daprbundle_linux_arm64.tar.gz \
file://config.yaml"
SRC_URI[sha256sum] = "ab690d876c5427c1143f5a4e2859874bf1f4fdb956cc2c00666c122af4f4bd70"

S = "${WORKDIR}"

INSANE_SKIP:${PN} = "already-stripped"

do_install () {
  install -d ${D}${bindir}
  install -m 0755 ${S}/daprbundle/dapr ${D}${bindir}
  install -d ${D}/home/root/.dapr/
  install -m 0644 ${S}/config.yaml ${D}/home/root/.dapr/
  install -d ${D}/home/root/.dapr/bin/
  tar --no-same-owner -xpf ${S}/daprbundle/dist/daprd_linux_arm64.tar.gz -C ${D}/home/root/.dapr/bin
  tar --no-same-owner -xpf ${S}/daprbundle/dist/dashboard_linux_arm64.tar.gz --strip-component=2 -C ${D}/home/root/.dapr/bin release/linux_arm64/web release/linux_arm64/dashboard
  tar --no-same-owner -xpf ${S}/daprbundle/dist/placement_linux_arm64.tar.gz -C ${D}/home/root/.dapr/bin
  install -d ${D}/home/root/.dapr/components
}

FILES:${PN} = "${bindir} /home/root/.dapr"

